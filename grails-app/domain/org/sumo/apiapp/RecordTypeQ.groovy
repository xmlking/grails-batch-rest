package org.sumo.apiapp

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeQ extends RecordType implements Comparable {
    static belongsTo = [drug: Drug]
    String 	Q017    //Reserve-1                          C001N00NN
    String 	Q018    //WAC Package Price                  N010Y02NN
    String 	Q028    //WAC Unit Price                     N013Y05NN
    String 	Q041    //Effective Date                     N008N00NN
    String 	Q049    //Older WAC Pkg Price                N010Y02NN
    String 	Q059    //Older WAC Unit Price               N013Y05NN
    String 	Q072    //Older WAC Eff Date                 N008N00NN
    String 	Q080    //Reserve-2                          C016N00NN

    static constraints = {
    }
    static mapping = {
        // we also cache collection type instances in 2nd level cache
        cache true
    }
}
