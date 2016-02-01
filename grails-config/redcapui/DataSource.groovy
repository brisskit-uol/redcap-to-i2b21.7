dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
	url = "jdbc:postgresql://localhost:5432/i2b2?searchpath=redcapi2b2tool"
	dialect = "org.hibernate.dialect.PostgreSQLDialect"
	username = "redcapi2b2tool"
	password = "redcapi2b2tool"
    dbCreate = "update" 
}

/*dataSource {
    pooled = true
    driverClassName = "net.sourceforge.jtds.jdbc.Driver"
	url = "jdbc:jtds:sqlserver://winxp1/sa"
	dialect = "org.hibernate.dialect.SQLServerDialect"
	username = "sa"
	password = "root"
    dbCreate = "create" 
}*/

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
