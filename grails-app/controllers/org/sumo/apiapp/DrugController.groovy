package org.sumo.apiapp

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import org.sumo.apiapp.Drug

@Secured(value=["hasRole('ROLE_USER')"])
class DrugController extends RestfulController<Drug> {

    static scaffold = true
    static responseFormats = ['html', 'json', 'xml', 'hal']
    static allowedMethods = [show: "GET"]

    DrugController() {
        super(Drug, true)
    }

    @Override
    def index(Integer pageSize) {
        params.max = Math.min(pageSize ?: 10, 100)
        // We pass which fields to be rendered with the includes attributes,
        // we exclude the class property for all responses. ***when includes are defined excludes are ignored.
        //params.fetch = [recordTypeRs:"eager"] from params.fields???
        respond resource.list(params),
                [includes: includeFields, excludes: ['class', 'errors', 'version'],
                 metadata: [total: countResources(), pageSize: params.max, offset: params.offset?:0, facets:[]],
                 model: [("${resourceName}InstanceCount".toString()): countResources()]]
    }

    def show(Drug drug) {
        respond drug,
                [includes: includeFields, excludes: ['class', 'errors', 'version']]
    }

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }

    def search(Integer pageSize) {
        params.pageSize = Math.min(pageSize ?: 10, 100)
        def c = Drug.createCriteria()
        def results = c.list(params) {
            //Your criteria here with params.q
            and {
                like('ndc', params.ndc?params.ndc+'%':'%')
                like('recordTypeJ.j017', params.labelerName?'%'+params.labelerName+'%':'%')
                like('recordTypeE.e017', params.productName?'%'+params.productName+'%':'%')
            }
            //cache(true)
        }
        def total = results.totalCount
        respond results,
                [includes: includeFields, excludes: ['class', 'errors', 'version'],
                 metadata: [total: total, pageSize: params.pageSize, offset: params.offset?:0, facets:[]],
                 model: [("${resourceName}InstanceCount".toString()): total]]
    }

    // query by example
    def search1(Integer max) {
        //params.max = Math.min(max ?: 10, 100)
        def example = new Drug(params)
        def results = Drug.findAll(example)
        respond results, model:[drugCount: results.size()]
    }

    def search2(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Map query = [:]
        def gormProps = Drug.getGormPersistentEntity().getPersistentProperties().groupBy { it.name }
        params.each {
            if(gormProps[it.key])
                query[it.key] = it.value.asType(gormProps[it.key].type)
        }

        respond Drug.findAllWhere(query,params)
    }

    @Secured(value=["hasRole('ROLE_BUSINESS_ADMIN')"])
    def test403() {
        def aSet = new HashSet()
        aSet.add('one')
        aSet.add('two')
        respond aSet
    }
}
