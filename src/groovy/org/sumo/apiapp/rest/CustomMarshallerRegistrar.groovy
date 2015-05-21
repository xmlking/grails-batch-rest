package org.sumo.apiapp.rest

import grails.converters.JSON

class CustomMarshallerRegistrar {

    void registerMarshallers() {

//        JSON.registerObjectMarshaller(org.sumo.apiapp.Provider) {
//            def map = [:]
//            map['id'] = it?.id
//            map['firstName'] = it?.firstName
//            map['middleName'] = it?.middleName
//            map['lastName'] = it?.lastName
//            map['gender'] = it?.gender
//            map['dateOfBirth'] = it?.dateOfBirth
//            map['effectiveDate'] = it?.effectiveDate
//            map['cancellationDate'] = it?.cancellationDate
//            map['npi'] = it?.npi
//            map['image'] = it?.image
//            map['rating'] = it?.rating
//            map['type'] = it?.type
//            map['contactInfo'] = it?.contactInfo
//            map['specialties'] = it?.specialties
//            map['toText'] = it?.toString()
//            return map
//        }

        JSON.registerObjectMarshaller(Enum) {
            return it.name()
        }

    }

}