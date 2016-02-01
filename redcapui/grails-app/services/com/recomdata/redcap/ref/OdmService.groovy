package com.recomdata.redcap.ref

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

import com.recomdata.i2b2.dao.I2B2DBUtils;
import com.recomdata.i2b2.I2B2ODMStudyHandlerCMLClient;

class OdmService {

    static transactional = true
	
	private ArrayList<HashMap<String, String>> importStatusList = null; 
	def repositoryPath = ConfigurationHolder.config.com.recomdata.odm.repositoryPath
	def importPath = ConfigurationHolder.config.com.recomdata.odm.inboxPath
	def archivePath = ConfigurationHolder.config.com.recomdata.odm.archivePath
	
    def serviceMethod() {

    }
	
	def getFilesFromRepository() throws FileNotFoundException {
		File odmFilesDir = new File(repositoryPath + importPath);
		File[] odmFiles = odmFilesDir.listFiles();
		if(odmFiles == null || odmFiles.length == 0) {
			throw new Exception ("No files were found in " + odmFilesDir + ".");
		}
		
		return odmFiles;
	}
	
	def initDbConnection() throws SQLException, ClassNotFoundException {
		def jdbcUrl 	= ConfigurationHolder.config.com.chb.i2b2.jdbcUrl
		def dbUser 		= ConfigurationHolder.config.com.chb.i2b2.dbUser
		def dbPass 		= ConfigurationHolder.config.com.chb.i2b2.dbPass
		def jdbcDriver 	= ConfigurationHolder.config.com.chb.i2b2.jdbcDriver
		def webService 	= ConfigurationHolder.config.com.chb.i2b2.webService
		
		//saj
		I2B2DBUtils.init(jdbcUrl, dbUser, dbPass, jdbcDriver, webService)
	}
	
	def importOdm() throws Exception {
		//String odmXmlPath, String jdbcUrl, String dbUser, String dbPass, String jdbcDriver, String repositoryPath, String inboxPath, String archivePath
		// client.loadODMFile2I2B2(odmXmlPath, jdbcUrl, dbUser, dbPass, jdbcDriver, repositoryPath, inboxPath, archivePath);
		//client.loadODMFile2I2B2(odmXmlPath);
		importStatusList = new ArrayList<HashMap<String, String>>();
		
		File[] odmFiles = getFilesFromRepository();
		
		initDbConnection();
		
		I2B2ODMStudyHandlerCMLClient client = new I2B2ODMStudyHandlerCMLClient();
		for (File odmFile : odmFiles) {
			loadODMFile2I2B2(client, odmFile);
		}						
		
		I2B2DBUtils.shutdown()
		return importStatusList;
	}
	
	def loadODMFile2I2B2(I2B2ODMStudyHandlerCMLClient client, File odmFile) {
		HashMap<String, String>hm = new HashMap<String, String>();
		try {			
			hm.put("name", odmFile.getName());
			client.loadODMFile2I2B2(odmFile.getAbsolutePath());
			archiveODMFile(odmFile);
			
			hm.put("status", "success");			
			hm.put("message", "");
			
		} catch(Exception ex) {
			hm.put("status", "failed");
			hm.put("message", ex.toString()); 			
		} 
				
		importStatusList.add(hm);
	}
	
	def archiveODMFile(File odmFile) {
		File archiveDir = new File(repositoryPath + archivePath);
		if (!archiveDir.exists()) {
			archiveDir.mkdirs();
		}
		
		// Get filename without file extension
		def odmFileName = odmFile.getName().substring(0, odmFile.getName().lastIndexOf('.'));
		def sdf = new SimpleDateFormat("yyyymmddhhmmss");
		def archiveFileName = odmFileName + "_" + sdf.format(new Date()) + ".xml";
		
		File archivedXml = new File(archiveDir, archiveFileName); 
		odmFile.renameTo(archivedXml);
	}
}
