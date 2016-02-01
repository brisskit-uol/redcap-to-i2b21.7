/*dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    //username = "sa"
    //password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
        //properties for JDBC added by Alex 9/27/2011
	    url = "jdbc:mysql://localhost:3306/rcdata"
	    username = "redcapui"
	    password = "chbpgd"
        dbCreate = "update" // one of 'create', 'create-drop','update'
		//logSql = true
		//debugSql = true
        }
    }
    test {
        dataSource {
             //properties for JDBC added by Alex 9/27/2011
	    url = "jdbc:mysql://localhost:3306/rcdata"
	    username = "redcapui"
	    password = "chbpgd"
        dbCreate = "update"
            //url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
             //properties for JDBC added by Alex 9/27/2011
	    url = "CHANGE_ME"
	    username = "CHANGE_ME"
	    password = "CHANGE_ME"
            //dbCreate = "update"
            //url = "jdbc:hsqldb:file:prodDb;shutdown=true"
        }
    }
}*/
