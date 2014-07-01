package org.sumo.apiapp

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeR extends RecordType implements Comparable {
    static belongsTo = [drug: Drug]
    String 	R017    //AWP Indicator Code                 C001N00YN
    String 	R018    //AWP Package Price                  N010Y02NN
    String 	R028    //AWP Unit Price                     N013Y05NN
    String 	R041    //AWP Effective Date                 N008N00NN
    String 	R049    //Older AWP Package Price            N010Y02NN
    String 	R059    //Older AWP Unit Price               N013Y05NN
    String 	R072    //Older AWP Effective Date           N008N00NN
    String 	R080    //Reserve                            C016N00NN

    static constraints = {
    }
    static mapping = {
        // we also cache collection type instances in 2nd level cache
        cache true
    }
}
