package org.sumo.apiapp

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.context.MessageSource
import org.springframework.web.servlet.support.RequestContextUtils as RCU

@Secured(value=["hasRole('ROLE_USER')"])
class TypeController {

    def index() {
        providers()
    }

    def providers() {
        render ProviderType.values().collect {[ "name" : it.name,  "value": it.value]} as JSON
    }

    def genders() {
        render GenderType.values().collect {[ "name" : it.name,  "value": it.value]} as JSON
    }

    def socialMedias() {
        render SocialMediaType.values().collect {[ "name" : it.name,  "value": it.value]} as JSON
    }

    def devices(){
        render DeviceType.values().collect {[ "name" : it.name(),  "value":message(message: it)]} as JSON
    }
}
