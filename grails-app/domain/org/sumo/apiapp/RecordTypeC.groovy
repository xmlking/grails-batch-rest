package org.sumo.apiapp

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeC extends RecordType implements Comparable {
    static belongsTo = [drug: Drug]
    String 	C017 //Old NDC-UPC-HRI                    C011N00NN
    String 	C028 //Reserve-1                          C001N00NN
    String 	C029 //Old Formatted Id Number            C013N00NN
    String	C042 //Old Eff Date                       N005N00NN
    String 	C047 //New NDC-UPC-HRI                    C011N00NN
    String 	C058 //Reserve-2                          C001N00NN
    String 	C059 //New Formatted Id Number            C013N00NN
    String 	C072 //New Eff Date                       N005N00NN
    String 	C077 //Reserve-3                          C019N00NN

    static constraints = {
    }
    static mapping = {
        // we also cache collection type instances in 2nd level cache
        cache true
    }
}