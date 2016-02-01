// set per-environment serverURL stem for creating absolute links
environments {
    production {
      grails.serverURL = "http://localhost/${appName}"
      
      //ODM FILE STORAGE LOCATIONS
      com.recomdata.odm.repositoryPath = "C:\\runtime\\repository"
      com.recomdata.odm.inboxPath="\\inbox"
      com.recomdata.odm.archivePath="\\archive"
      
      //REDCAP WEBSERVICE URLS
      //com.chb.redcap.baseUrl="http://spss-interview.tch.harvard.edu/REDCap_Service/"
      com.chb.redcap.baseUrl="http://192.168.52.132/redcap_service/"
      com.chb.redcap.metaDataUrl="GetProjectMetadata.php"
      com.chb.redcap.recordUrl="GetProjectClinicalData.php"
      
      //i2b2 Database connection settings.
      com.chb.i2b2.jdbcUrl="jdbc:postgresql://localhost:5432/i2b2?searchpath=project2"
      com.chb.i2b2.dbUser="project2"
      com.chb.i2b2.dbPass="project2"
      com.chb.i2b2.jdbcDriver="org.postgresql.Driver"

    }
   
   development {
      grails.serverURL = "http://localhost/${appName}"
      
      //ODM FILE STORAGE LOCATIONS
      com.recomdata.odm.repositoryPath = "C:\\runtime\\repository"
      com.recomdata.odm.inboxPath="\\inbox"
      com.recomdata.odm.archivePath="\\archive"
      
      //REDCAP WEBSERVICE URLS
      //com.chb.redcap.baseUrl="http://spss-interview.tch.harvard.edu/REDCap_Service/"
      com.chb.redcap.baseUrl="http://192.168.52.132/redcap_service/"
      com.chb.redcap.metaDataUrl="GetProjectMetadata.php"
      com.chb.redcap.recordUrl="GetProjectClinicalData.php"
      
      //i2b2 Database connection settings.
      com.chb.i2b2.jdbcUrl="jdbc:jtds:sqlserver://192.168.41.211:1433/i2b2metadata"
      com.chb.i2b2.dbUser="cit_user"
      com.chb.i2b2.dbPass="cit_user"
      com.chb.i2b2.jdbcDriver="net.sourceforge.jtds.jdbc.Driver"

    }
}
