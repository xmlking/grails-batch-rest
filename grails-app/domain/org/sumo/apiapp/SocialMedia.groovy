package org.sumo.apiapp

import org.bson.types.ObjectId

class SocialMedia {

    ObjectId id

    SocialMediaType type
    String value

    static constraints = {
        type  nullable: false
        value blank: false, nullable: false
    }
}