import grails.plugins.springsecurity.SecurityConfigType

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

grails.config.locations = [ ]

grails.config.locations = []
def defaultConfigFiles = [
	"/var/local/brisskit/i2b2/runtime/grails-config/redcapui/Config.groovy",
	"/var/local/brisskit/i2b2/runtime/grails-config/redcapui/DataSource.groovy"
]
defaultConfigFiles.each { filePath ->
	def f = new File(filePath)
	if (f.exists()) {
		grails.config.locations << "file:${filePath}"
	} else {
		println("Config file not found");
	}
}
println "(*) grails.config.locations = ${grails.config.locations}"
println "--------------------------------------------------------"


tomcat.deploy.username="manager"
tomcat.deploy.password="secret"
tomcat.deploy.url="http://localhost:8070/manager"

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.recomdata.redcap.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.recomdata.redcap.UserRole'
grails.plugins.springsecurity.authority.className = 'com.recomdata.redcap.Role'
// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
    
    appenders {
        file name:'file', file: '/var/local/brisskit/i2b2/runtime/logs/log.txt'
    }
    root {
        info 'stdout', 'file'
    }

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}
