package org.sumo.apiapp

import org.bson.types.ObjectId

class Device {

    ObjectId id

    DeviceType type
    String value

    static constraints = {
        type  nullable: false
        value blank: false, nullable: false
    }
}
