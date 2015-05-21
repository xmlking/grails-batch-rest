package org.sumo.apiapp

import groovy.transform.ToString
import org.bson.types.ObjectId

@ToString(includeNames = true, includeFields = true)
class Contact {

    ObjectId id

    Address address

    static hasMany = [devices: Device]
    static embedded = ['address','devices']


    static mapping = {
        cache true
    }
}
