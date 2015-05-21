//https://www.aamc.org/cim/specialty/list/us/

package org.sumo.apiapp

import groovy.transform.ToString
import org.bson.types.ObjectId

@ToString(includeNames = true, includeFields = true)
class Specialty {

    ObjectId id

    String name
    String description
    String image
    String code

    Specialty parent

    static mapping = {
        index name:"text"
    }
    static constraints = {
        name        blank: false, nullable: false
        description blank: true, nullable: true
        image       blank: true, nullable: true
        code        blank: true, nullable: true
        parent      nullable: true
    }
}
