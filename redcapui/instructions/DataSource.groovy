dataSource {
    pooled = true
    driverClassName = "net.sourceforge.jtds.jdbc.Driver"
	url = "jdbc:jtds:sqlserver://RECOM-91E800AC0:1433/rcdata;instance=SQLEXPRESS"
	dialect = "org.hibernate.dialect.SQLServerDialect"
	username = "redcapui"
	password = "chbpgd"
    dbCreate = "update" 
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
/*    production {
        dataSource {
		url = "jdbc:jtds:sqlserver://localhost:1433/rcdata"
	    username = "redcapui"
	    password = "chbpgd"
        dbCreate = "create" // one of 'create', 'create-drop','update'
		logSql = true
		debugSql = true
        }
    }
	
	development {
        dataSource {
		url = "jdbc:jtds:sqlserver://localhost:1433/rcdata"
		username = "redcapui"
	    password = "chbpgd"
        dbCreate = "create" // one of 'create', 'create-drop','update'
		logSql = true
		debugSql = true
        }
    }
*/
