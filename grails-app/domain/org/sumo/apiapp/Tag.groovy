package org.sumo.apiapp

import org.bson.types.ObjectId

class Tag {

    ObjectId id

    String text

    static mapping = {
        text index: true
        cache true
    }

    static constraints = {
        text blank: false, nullable: false, maxSize: 20
    }
}