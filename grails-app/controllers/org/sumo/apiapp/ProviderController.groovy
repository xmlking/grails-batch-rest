package org.sumo.apiapp

import grails.plugin.springsecurity.annotation.Secured
import org.sumo.apiapp.rest.PagedRestfulController

@Secured(value=["hasRole('ROLE_USER')"])
class ProviderController extends PagedRestfulController<Provider> {

    ProviderController ( ) {
        super(Provider)
    }

    def show(Provider provider) {
        respond provider,
                [includes: getIncludeFields(), excludes: ['class', 'errors', 'version'],
                 formats:['json','xml']]
    }

}