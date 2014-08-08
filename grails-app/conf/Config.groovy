// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = [ "classpath:${appName}-config.properties",
                             "classpath:${appName}-config.groovy",
                             "file:${userHome}/.grails/${appName}-config.properties",
                             "file:${userHome}/.grails/${appName}-config.groovy"]

if (System.properties["${appName}.config.location"]) {
    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
}

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml'],
    appcache:      'text/cache-manifest'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    appenders {
        appender new org.sumo.apiapp.WebLogAppender(
                source:'apiApp', name: 'webLogAppender',
                layout:new org.apache.log4j.EnhancedPatternLayout(conversionPattern: '%d [%t] %-5p %c{2} - %m'))
        console name:'stdout'
    }
    root {
        info 'stdout', 'webLogAppender'
    }

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    //SUMO
    debug  'grails.app'
    // debug  'org.hibernate.cache.ehcache'
    debug  'org.sumo.apiapp'
    info   'grails.plugin.springsecurity.web.filter.DebugFilter'
}

//Spring Batch Config
plugin {
    springBatch {
        jmx {
            enable = true
            remote {
                enable = true
            }
        }
        loadTables = true
        database = "h2"
    }
}
environments {
    production {
        plugin {
            springBatch {
                // database = "oracle10g" //TODO set prod database type...
            }
        }
    }
}

//GORM
grails.gorm.default.constraints = {
    '*'(nullable: true)
    myShared(nullable: true, size: 1..20)
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.sumo.apiapp.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.sumo.apiapp.security.UserRole'
grails.plugin.springsecurity.authority.className = 'org.sumo.apiapp.security.Role'

// Basic Auth: Enable this during testing REST API via curl or RESTClient
//grails.plugin.springsecurity.useBasicAuth = true
//grails.plugin.springsecurity.basic.realmName = "Ralph's Bait and Tackle"
//grails.plugin.springsecurity.filterChain.chainMap = [
//        '/drugs/**': 'JOINED_FILTERS,-exceptionTranslationFilter',
//        '/**': 'JOINED_FILTERS,-basicAuthenticationFilter,-basicExceptionTranslationFilter'
//]

//Supports Acyclic graph (multiple inheritance , circular dependency detected )
grails.plugin.springsecurity.roleHierarchy = '''
    ROLE_SUPER_ADMIN > ROLE_SWITCH_USER
    ROLE_SWITCH_USER > ROLE_BUSINESS_ADMIN
    ROLE_BUSINESS_ADMIN > ROLE_IT_ADMIN
    ROLE_BUSINESS_ADMIN > ROLE_DATA_ADMIN
    ROLE_IT_ADMIN > ROLE_USER
    ROLE_DATA_ADMIN > ROLE_USER
'''

security.groupToRoleMap = [
        "ROLE_RXCSTD_ADMIN":"ROLE_SUPER_ADMIN",
        "ROLE_RXCSTD_CSR_VIEW_ONLY":"ROLE_USER",
        "ROLE_RXCSTD_CSR_READ_WRITE":"ROLE_USER",
        "ROLE_RXCSTD_CSR_MANAGER":"ROLE_BUSINESS_ADMIN",
        "ROLE_RXCSTD_EBIZ_ANALYST":"ROLE_DATA_ADMIN",
        "ROLE_RXCSTD_EBIZ_EP_DEPLOY_TST":"ROLE_IT_ADMIN",
        "ROLE_RXCSTD_EBIZ_EP_DEPLOY_STG":"ROLE_IT_ADMIN",
        "ROLE_RXCSTP_EBIZ_EP_DEPLOY_PRD":"ROLE_SWITCH_USER"//"ADMIN_PRD"//TODO
]

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        '/':                              ['permitAll'],
        '/index':                         ['permitAll'],
        '/index.gsp':                     ['permitAll'],
        '/stomp/**': 			          ['permitAll'], //TODO: ROLE_USER not working. redirect login page ever after successfully logged-in/
        '/api/**':                        ['ROLE_USER'],
        '/assets/**':                     ['permitAll'],
        '/**/js/**':                      ['permitAll'],
        '/**/css/**':                     ['permitAll'],
        '/**/images/**':                  ['permitAll'],
        '/**/favicon.ico':                ['permitAll'],
        '/j_spring_security_switch_user': ['ROLE_SWITCH_USER'],
        '/j_spring_security_exit_user':   ['permitAll'],
        '/dbdoc/**':   				  	  ['ROLE_IT_ADMIN'],
        '/dbconsole/**':   				  ['ROLE_IT_ADMIN'],
        '/admin/**':       			  	  ['ROLE_BUSINESS_ADMIN'],
        '/grails-errorhandler/**': 	      ['permitAll'],
        '/grails/error/**':        	      ['permitAll'],
        '/platformTools/**':              ['ROLE_DATA_ADMIN'],
        '/springBatch*/**':               ['ROLE_DATA_ADMIN'],
        '/batch/**': 			          ['ROLE_DATA_ADMIN']
]

grails.plugin.springsecurity.logout.handlerNames = ['rememberMeServices', 'securityContextLogoutHandler']

grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'org.sumo.apiapp.security.PersistentLogin'

// LDAP config
// NOTE:Be careful about ampersand symbols within your LDAP search filters. They must be escaped!
grails.plugin.springsecurity.ldap.useRememberMe = false
grails.plugin.springsecurity.ldap.auth.hideUserNotFoundExceptions = false

// Overwriting with settings from Batch-config.properties
grails.plugin.springsecurity.ldap.context.server = 'ldap://ad.sumo.com:389/'
grails.plugin.springsecurity.ldap.context.managerDn = 'Using property setting in ~/.grails/apiApp-config.properties'
grails.plugin.springsecurity.ldap.context.managerPassword = 'Using property setting in ~/.grails/apiApp-config.properties'

// LDAP BindAuthenticator (we are not using PasswordComparisonAuthenticator)
grails.plugin.springsecurity.ldap.authenticator.useBind = true
grails.plugin.springsecurity.ldap.authenticator.dnPatterns = ['cn={0},CN=Users,DC=public,DC=portal,DC=sumo,DC=com','cn={0}']

// LDAP FilterBasedLdapUserSearch
grails.plugin.springsecurity.ldap.search.base = 'CN=Users,DC=public,DC=portal,DC=sumo,DC=com'
grails.plugin.springsecurity.ldap.search.filter="sAMAccountName={0}" // for Active Directory you need this
grails.plugin.springsecurity.ldap.search.timeLimit = 30000

// Authorities
grails.plugin.springsecurity.ldap.authorities.retrieveGroupRoles = true
grails.plugin.springsecurity.ldap.authorities.groupSearchBase ='CN=Users,DC=public,DC=portal,DC=sumo,DC=com'
// If you don't want to support group membership recursion (groups in groups), then use the following setting
grails.plugin.springsecurity.ldap.authorities.groupSearchFilter = 'member={0}' // Active Directory specific
// If you wish to support groups with group as members (recursive groups), use the following
//grails.plugin.springsecurity.ldap.authorities.groupSearchFilter = '(member:1.2.840.113556.1.4.1941:={0})' // Active Directory specific
grails.plugin.springsecurity.ldap.authorities.searchSubtree = false
grails.plugin.springsecurity.ldap.authorities.defaultRole='ROLE_USER' //FIXME : remove me after all IFMS business users are provisioned in MS LDAP.
grails.plugin.springsecurity.ldap.authorities.ignorePartialResultException = true // typically needed for Active Directory


//changed default order of providers during development ONLY. Make DAO before LDAP Auth Provider
environments {
    development {
        grails.plugin.springsecurity.providerNames = [
                'daoAuthenticationProvider',
                'ldapAuthProvider',
                'anonymousAuthenticationProvider',
                'rememberMeAuthenticationProvider'
        ]
    }
}

//TODO test later if we still need it...
//Overriding default behavior, because Angular is not sending X-Requested-With header due to CORS issue.
grails.plugin.springsecurity.ajaxCheckClosure = { request ->  request.getHeader("Accept")?.equalsIgnoreCase('application/json') }


