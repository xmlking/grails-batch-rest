package org.sumo.apiapp

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeS extends RecordType implements Comparable {
    static belongsTo = [drug: Drug]
    String 	S017    //Reserve-1                          C001N00NN
    String 	S018    //DP Package Price                   N010Y02NN
    String 	S028    //DP Unit Price                      N013Y05NN
    String 	S041    //DP Effective Date                  N008N00NN
    String 	S049    //Older DP Package Price             N010Y02NN
    String 	S059    //Older DP Unit Price                N013Y05NN
    String 	S072    //Older DP Effective Date            N008N00NN
    String 	S080    //Reserve-2                          C016N00NN

    static constraints = {
    }
    static mapping = {
        // we also cache collection type instances in 2nd level cache
        cache true
    }
}
