package org.sumo.apiapp

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeT extends RecordType implements Comparable {
    static belongsTo = [drug: Drug]
    String 	T017 //Reserve-1                          C001N00NN
    String 	T018 //CMS FUL Price                      N013Y05NN
    String 	T031 //CMS FUL Price Eff Date             N008N00NN
    String 	T039 //1st Oldest CMS FUL Price           N013Y05NN
    String 	T052 //1st Oldest CMS FUL Eff Dt          N008N00NN
    String 	T060 //2nd Oldest CMS FUL Price           N013Y05NN
    String 	T073 //2nd Oldest CMS FUL Eff Dt          N008N00NN
    String 	T081 //Reserve-2                          C015N00NN

    static constraints = {
    }
    static mapping = {
        // we also cache collection type instances in 2nd level cache
        cache true
    }
}
