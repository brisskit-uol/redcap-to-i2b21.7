// set per-environment serverURL stem for creating absolute links
environments {
    production {
      grails.serverURL = "http://localhost:8080/${appName}"   
      
      //ODM FILE STORAGE LOCATIONS
      com.recomdata.odm.repositoryPath = "/var/local/brisskit/i2b2/runtime/repository"
      com.recomdata.odm.inboxPath="/inbox"
      com.recomdata.odm.archivePath="/archive"
      
      //REDCAP WEBSERVICE URLS
      //com.chb.redcap.baseUrl="http://spss-interview.tch.harvard.edu/REDCap_Service/"
      com.chb.redcap.baseUrl="https://lrbredcaptest.lrbru.xuhl-tr.nhs.uk/redcap_service/"
      com.chb.redcap.metaDataUrl="GetProjectMetadata.php"
      com.chb.redcap.recordUrl="GetProjectClinicalData.php"
      
      //i2b2 Database connection settings.
      com.chb.i2b2.jdbcUrl="jdbc:postgresql://localhost:5432/i2b2?searchpath=project2"
      com.chb.i2b2.dbUser="project2"
      com.chb.i2b2.dbPass="project2"
      com.chb.i2b2.jdbcDriver="org.postgresql.Driver"
      com.chb.i2b2.webService="http://lrbaddtest.lrbru.xuhl-tr.nhs.uk/api/research/get-redcap-rids?apikey=12345678&redcap_project=exceed"

    }
   
   development {
      grails.serverURL = "http://localhost:8080/${appName}"
      
      //ODM FILE STORAGE LOCATIONS
      com.recomdata.odm.repositoryPath = "/var/local/brisskit/i2b2/redcap-to-i2b21.7/repository"
      com.recomdata.odm.inboxPath="/inbox"
      com.recomdata.odm.archivePath="/archive"
      
      //REDCAP WEBSERVICE URLS
      //com.chb.redcap.baseUrl="http://spss-interview.tch.harvard.edu/REDCap_Service/"
      com.chb.redcap.baseUrl="https://<server>/redcap_service/"
      com.chb.redcap.metaDataUrl="GetProjectMetadata.php"
      com.chb.redcap.recordUrl="GetProjectClinicalData.php"
      
      //i2b2 Database connection settings.
      com.chb.i2b2.jdbcUrl="jdbc:postgresql://<pg_server>:5432/i2b2?searchpath=<project>"
      com.chb.i2b2.dbUser="<project_name>"
      com.chb.i2b2.dbPass="<project_name>"
      com.chb.i2b2.jdbcDriver="org.postgresql.Driver"
      com.chb.i2b2.webService="http://<server>/api/research/get-redcap-rids?apikey=12345678&redcap_project=<project>"

    }
}
