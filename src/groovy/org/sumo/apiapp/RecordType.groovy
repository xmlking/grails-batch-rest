package org.sumo.apiapp

import groovy.transform.ToString
import org.bson.types.ObjectId
import org.sumo.apiapp.Drug

@ToString(includeNames = true, includeFields = true, includePackage = false, excludes = 'dateCreated,lastUpdated,metaClass')
abstract class RecordType implements Comparable {

    ObjectId id

    String  recordCode          //Record Code                        C003N00YN
    String 	transactionCode     //Transaction Code                   C001N00YN
    //Truncated Julian Day (TJD)
//    @BindUsing({
//        obj, source -> source['lastChangeDate']?.toUpperCase()
//    })
//    Date 	lastChangeDate      //Last Change Date                   N005N00NN
    String 	lastChangeDate      //Last Change Date                   N005N00NN

    static belongsTo = [drug: Drug]

    static constraints = {
        recordCode size: 1..3//, blank: false
        transactionCode size:1..1
        lastChangeDate size:5..5
    }
    static mapping = {
        tablePerHierarchy       false
    }


    public int compareTo(def other) {
        return recordCode <=> other?.recordCode // <=> is the compareTo operator in groovy
    }
}
