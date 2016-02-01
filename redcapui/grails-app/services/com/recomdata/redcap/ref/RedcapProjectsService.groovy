package com.recomdata.redcap.ref

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.SQLException;

import com.recomdata.i2b2.I2B2ODMStudyHandler;
import com.recomdata.i2b2.dao.I2B2DBUtils;
import com.recomdata.odm.ODMLoader;
import com.recomdata.redcap.odm.Redcap2ODM;
import com.recomdata.redcap.ws.GetRedcapService;

import org.cdisk.odm.jaxb.ODM;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;
import org.json.JSONObject;
import org.json.JSONArray;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;

import com.recomdata.redcap.dm.Projects;
import com.recomdata.redcap.dm.Servers;


class RedcapProjectsService {

    	static transactional = false

   	/**
   	 * -Initializes an instance of the GetRedcapService.
   	 * -Makes a call to GetAllProjects from the GetRedcapService to get projects for the server specified by the incoming serverId
   	 * -Parses the json response and saves any new projects returned to the local cache.
   	 * -Updates the local cache and removes any projects not returned in the response.
	 */	
	def List<RedcapProjects> getAllRedcapProjects(id) {
		long serverIdL = Long.parseLong(id)
		Servers server = Servers.get(serverIdL)
		def baseURL = server.baseURL
	
		List<RedcapProjects> redcapProjectsInstanceList = new ArrayList<RedcapProjects>()
		List<String> redcapProjectsInstanceIdList = new ArrayList<String>()
		RedcapProjects redcapProjectsInstance = null
		
		String endpoint = baseURL + "GetAllProjects.php"
		GetRedcapService redcapWs = new GetRedcapService()
		//for CHB API
		Map<String, String> params = new HashMap<String, String>()
		//for chb api sites, no param passed
		params.put("","")
		String jsonString = redcapWs.getRedcapWebServicedata(endpoint, params)
		jsonString = jsonString.substring(jsonString.indexOf("["),jsonString.lastIndexOf("}"))
		JSONObject json = null
		JSONArray jsonArry = new JSONArray(jsonString)
		
		for(int i = 0; i < jsonArry.length(); i++) {
			json = (JSONObject)jsonArry.get(i)
			
			redcapProjectsInstance = new RedcapProjects()
			redcapProjectsInstance.projectID = json.getString("project_id")
			redcapProjectsInstance.projectName = json.getString("name")
			redcapProjectsInstance.projectName = redcapProjectsInstance.projectName.replaceAll("/", "")
			redcapProjectsInstance.projServerId = serverIdL
			redcapProjectsInstance.serverName = server.name

			//Persist the incoming project information in the database
			//Create a new project if it does not exist
			Projects project = Projects.findByProjectID(redcapProjectsInstance.projectID)
			if(!project){
				project = new Projects()
				project.projectID = redcapProjectsInstance.projectID
				project.server = server
				project.name = redcapProjectsInstance.projectName
				project.save(flush : true)
			}else{//Set the enabled flag from db
				redcapProjectsInstance.isEnabled = project.selected
				redcapProjectsInstance.token = project.token
				redcapProjectsInstance.status = project.status
				redcapProjectsInstance.lastProcessDate = convertDate(project.lastProcessed)
				redcapProjectsInstance.serverName = project.server.name
				
				// Update database with project name if changed.
				if(!(project.name.equals(redcapProjectsInstance.projectName))) {
					project.name = redcapProjectsInstance.projectName;
					project.save(flush : true)
				} 				
			}
			redcapProjectsInstanceList.add(redcapProjectsInstance)
			redcapProjectsInstanceIdList.add(redcapProjectsInstance.projectID)
		}
		
		def projectList = Projects.getAll()
		projectList.each {project->
			if (!redcapProjectsInstanceIdList.contains(project.projectID)){
				project.delete()
			}
		}
		
		return redcapProjectsInstanceList
	}
	
	def convertDate(java.sql.Timestamp date){
		if(date==null){
			return ""
		}
		Calendar calendar = Calendar.getInstance()
		calendar.setTime(date)
		
		int month = calendar.get(Calendar.MONTH)+1
		int day = calendar.get(Calendar.DATE)
		int year = calendar.get(Calendar.YEAR)
		int hour = calendar.get(Calendar.HOUR)
		int minute = calendar.get(Calendar.MINUTE)
		int second = calendar.get(Calendar.SECOND)
		
		return month+"/"+day+"/"+year+" "+hour+":"+minute+":"+second
	}
	
	/**
	* method returns a list of ProjectID in domain object Projects from the projects table
	* @param serverURL
	* @return
	*/
   def List<String> getUnprocessedProjects(String serverURL) {
	   List<String> pLists = new ArrayList<String>()
	   
	   //HQL query
	   StringBuffer sb = new StringBuffer()
	   sb.append("select p.projectID ")
	   sb.append("from Projects p ")
	   sb.append("where p.serverName = :baseURL ")
	   
	   //query params
	   Map<String, Object> hm = new HashMap<String, Object>()
	   hm.put("baseURL",serverURL)
	   
	   pLists = Projects.executeQuery(sb.toString(), hm);
	   
	   return pLists;
   }
   
   
   /**
    * Toggle the selected flag for a project
    * @param projectId
    * @param serverUrl
    * @return
    */
   def toggleProjectSelection(projectId, serverId){
	   long serverIdL = Long.parseLong(serverId)
	   Servers server = Servers.get(serverIdL)
	   Projects project = Projects.findByProjectIDAndServer(projectId,server)
	   project.selected = !project.selected
	   project.save(flush:true)
   }
   
   
   def processRedcapProjects(serverId){
	   def repositoryPath = ConfigurationHolder.config.com.recomdata.odm.repositoryPath
	   def inboxPath = ConfigurationHolder.config.com.recomdata.odm.inboxPath
	   def archivePath = ConfigurationHolder.config.com.recomdata.odm.archivePath
	   def metaDataUrl = ConfigurationHolder.config.com.chb.redcap.metaDataUrl
	   def recordUrl = ConfigurationHolder.config.com.chb.redcap.recordUrl
	   def jdbcUrl =  ConfigurationHolder.config.com.chb.i2b2.jdbcUrl
	   def dbUser =  ConfigurationHolder.config.com.chb.i2b2.dbUser
	   def dbPass =  ConfigurationHolder.config.com.chb.i2b2.dbPass
	   def jdbcDriver =  ConfigurationHolder.config.com.chb.i2b2.jdbcDriver
	   def webService = ConfigurationHolder.config.com.chb.i2b2.webService
		
	   //saj
	   
	   List<Projects> selectedProjects = null
	   Redcap2ODM redcapOdm = null
	   try{
		   long serverIdL = Long.parseLong(serverId)
		   selectedProjects = Projects.findAllBySelectedAndServer(true, Servers.get(serverIdL))
		   
		   I2B2DBUtils.init(jdbcUrl, dbUser, dbPass, jdbcDriver, webService)
		   redcapOdm = new Redcap2ODM();
	   }catch(NumberFormatException nfe){
	   		log.error("Invalid server ID")
			log.error(nfe.getMessage())
	   }catch(SQLException sqe){
	   		log.error("Could not establish db connection")
			log.error(sqe.getMessage())
	   }catch(ClassNotFoundException cnfe){
	   		log.error("Could not initialize db driver")
			log.error(cnfe.getMessage())
	   }
	   
	   def xmlStatus=new HashMap()
	   selectedProjects.each{selectedProject->
		   try{
			   java.util.Date currentDate = new java.util.Date()
			   selectedProject.status = 'Started'
			   selectedProject.lastProcessed = new java.sql.Timestamp(currentDate.getTime())
			   selectedProject.save(flush:true)
			   
			   def baseUrl = selectedProject.server.baseURL
			   def projectDesc = "This project is retrieved from API site:"+ baseUrl
		   
		   
			   //created ODM object with RedCap metadata and clinical data
			   if(selectedProject.token==null || selectedProject.token==""){
				   throw new Exception("No valid token specified for project "+selectedProject.projectID)
			   }
			   ODM odm = redcapOdm.buildODM(selectedProject.projectID, selectedProject.name, projectDesc, 
				   							baseUrl, metaDataUrl, recordUrl, selectedProject.token);
										   
			   def odmFileName = createODMFile(repositoryPath, inboxPath, odm, selectedProject.server.name, selectedProject.name, selectedProject.projectID);
			   
			   
			   
			   //parse ODM XML and save as i2b2 metadata and demodata records
			   I2B2ODMStudyHandler odmHandler = new I2B2ODMStudyHandler(odm);
			   odmHandler.processODM();
			   
			   archiveODMFile(odmFileName, repositoryPath, archivePath, selectedProject.name, selectedProject.projectID, selectedProject.server.name)
			   
			   selectedProject.status = 'Finished'
			   selectedProject.save(flush:true)
			   
			   xmlStatus.put(selectedProject.id, "Finished")			   			   
		   } catch(Exception e) {
		   		log.error(e.getMessage())
				println("\n")
		   		e.printStackTrace()
				selectedProject.status = 'Failed'
				xmlStatus.put(selectedProject.id, "Failed")
		   }		   		  
	   }
	   
	   I2B2DBUtils.shutdown()
	   
	   return xmlStatus
   }
   
   def createODMFile(String repositoryPath, String inboxPath, ODM odm, String serverName, String projectName, String projectId){
	   ODMLoader odmLoader = new ODMLoader();
	   String odmFileName = repositoryPath + inboxPath +
					   File.separator + getOdmFileName(serverName, projectName, projectId)+".xml";
	   BufferedWriter out = new BufferedWriter(new FileWriter(odmFileName));
	   odmLoader.marshall(odm, out);
	   
	   log.info("Saved ODM file to "+ odmFileName);

	   out.close();
	   return odmFileName
   }
   
   def archiveODMFile(String odmXmlPath, String repositoryPath, String archivePath, String projectName, String projectId, String serverName){
	   File xml = new File(odmXmlPath);
		if (!xml.exists()) {
		   throw new FileNotFoundException("ODM file "+odmXmlPath+"not found");
		}else{
		   File archiveDir = new File(repositoryPath+archivePath);
		   if (!archiveDir.exists()) {
			   archiveDir.mkdirs();
		   }
		   
		   def currentDate = new java.util.Date()
		   
		   File archivedXml = new File(archiveDir, (getOdmFileName(serverName, projectName, projectId)+currentDate.getTime()+".xml"));
		   xml.renameTo(archivedXml);
		}
   }
   
   def getOdmFileName(String serverName, String projectName, String projectId){
	   return "REDCap_ODM_ "+serverName+"_"+projectName+"_"+projectId;
   }
   
   def persistToken(projectId, serverId, token) {
	   long serverIdL = Long.parseLong(serverId)
	   Servers server = Servers.get(serverIdL)
	   Projects project = Projects.findByProjectIDAndServer(projectId,server)
	   project.token = token
	   project.save(flush:true)
   }
   
}
