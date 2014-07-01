package org.sumo.apiapp

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeP extends RecordType implements Comparable {
    static belongsTo = [drug: Drug]
    String 	P017 //Ingredient Generic ID Type Code    C001N00YN
    String 	P018 //Ingredient Generic ID Number       N009N00NN
    String 	P027 //Ingredient Strength                N013Y05NN
    String 	P040 //Strength Unit Measure              C011N00NN
    String 	P051 //Generic Ingredient Name            C040N00NN
    String 	P091 //Active/Inactive Ingredient Flag    C001N00YN
    String 	P092 //Reserve                            C004N00NN

    static constraints = {
    }
    static mapping = {
        // we also cache collection type instances in 2nd level cache
        cache true
    }
}
