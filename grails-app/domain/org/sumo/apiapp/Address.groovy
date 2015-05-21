package org.sumo.apiapp

import groovy.transform.ToString
import org.bson.types.ObjectId
import grails.mongodb.geo.Point

@ToString(includeNames = true, includeFields = true)
class Address {

    ObjectId id

    String street
    String city
    String state
    String zip
    Point location

    static mapping = {
        location geoIndex:'2dsphere'
    }
    static constraints = {
        location nullable: true
    }
}
