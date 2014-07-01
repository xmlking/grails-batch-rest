// Place your Spring DSL code here
import org.springframework.beans.propertyeditors.CustomDateEditor
import java.text.SimpleDateFormat
import grails.rest.render.json.JsonRenderer
import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.hal.HalJsonRenderer
import grails.rest.render.hal.HalJsonCollectionRenderer
import org.sumo.apiapp.security.User
import org.sumo.apiapp.security.CustomUserDetails
import org.sumo.apiapp.security.CustomUserDetailsService
import org.sumo.apiapp.security.CustomDetailsContextMapper
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import  org.sumo.apiapp.render.SumoJsonCollectionRenderer

// Place your Spring DSL code here
beans = {
//  springConfig.addAlias('persistenceInterceptor', 'mongoPersistenceInterceptor')  //??? FIXME use this when using mongoDB plugin
//	springConfig.addAlias('transactionManager', 'mongoTransactionManager') //FIXME use this when using mongoDB plugin


    userRenderer(JsonRenderer,  User) {
        excludes = ['class', 'password']
    }
    customUserDetailsRenderer(JsonRenderer,  CustomUserDetails) {
        excludes = ['class', 'password']
    }
    grantedAuthorityRenderer(JsonRenderer,  GrantedAuthority) {
        excludes = ['class']
    }
    grantedAuthoritysRenderer(JsonCollectionRenderer,  GrantedAuthority) {
        excludes = ['class']
    }
    simpleGrantedAuthorityRenderer(JsonRenderer,  SimpleGrantedAuthority) {
        excludes = ['class']
    }
    simpleGrantedAuthoritysRenderer(JsonCollectionRenderer,  SimpleGrantedAuthority) {
        excludes = ['class']
    }

    // register Renderers/CollectionRenderers for all domain classes in the application.
    for (domainClass in grailsApplication.domainClasses) {
//        "json${domainClass.shortName}CollectionRenderer"(JsonCollectionRenderer, domainClass.clazz)
        "json${domainClass.shortName}CollectionRenderer"(SumoJsonCollectionRenderer, domainClass.clazz)
        "json${domainClass.shortName}Renderer"(JsonRenderer, domainClass.clazz)
        "hal${domainClass.shortName}CollectionRenderer"(HalJsonCollectionRenderer, domainClass.clazz)
        "hal${domainClass.shortName}Renderer"(HalJsonRenderer, domainClass.clazz)
    }

    userDetailsService(CustomUserDetailsService) {
        grailsApplication = ref('grailsApplication')
    }
    ldapUserDetailsMapper(CustomDetailsContextMapper) {
        groupToRoleMap = application.config.security.groupToRoleMap
    }

}