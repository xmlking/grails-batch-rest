package org.sumo.apiapp

import groovy.transform.ToString
import grails.rest.Resource
import org.bson.types.ObjectId

@ToString(includeNames = true, includeFields = true, includePackage = false, excludes = 'dateCreated,lastUpdated,metaClass')
@Resource //to add link method for HATEOS rendering
class Drug {

    ObjectId id

	String ndc

	RecordTypeA recordTypeA
    RecordTypeE recordTypeE
    RecordTypeG recordTypeG
    RecordTypeJ recordTypeJ
    RecordTypeL recordTypeL
    RecordTypeM recordTypeM

    static embedded = ['recordTypeA', 'recordTypeE', 'recordTypeG', 'recordTypeJ', 'recordTypeL', 'recordTypeM']
    static hasMany = [recordTypePs: RecordTypeP,recordTypeQs: RecordTypeQ,recordTypeRs: RecordTypeR,recordTypeSs: RecordTypeS,recordTypeTs: RecordTypeT,recordTypeCs: RecordTypeC]

    static constraints = {
		ndc (blank: false, nullable: false, maxSize:12) //unique:true
    }
	static mapping = {
        // we cache Drug instance and collection type associations in 2nd level cache.
        // caching should be enabled for each individual collection classes as well.
        // no need to eager fetch associations as we are doing caching.
        cache true
        recordTypeCs cache: true
        recordTypePs cache: true
        recordTypeQs cache: true
        recordTypeRs cache: true
        recordTypeSs cache: true
        recordTypeTs cache: true

        ndc index:true
	}

}

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeA extends RecordType implements Comparable {

	String 	A017 //Sequence Code                      C007N00NN
    String  A024 //Labeler Code                       N005N00NN
	String 	A029 //Generic ID Type Code               C001N00YN
    String  A030 //Generic ID Number                  N009N00NN
	String 	A039 //DEA Class Code                     C005N00YN
    String  A044 //AHFSCC Therapeutic Class Code      N006N00NN
	String 	A050 //Item Status Flag                   C001N00YN
	String 	A051 //Local/Systemic Flag                C001N00YN
	String 	A052 //TEE Code                           C002N00YN
	String 	A054 //Formatted ID Number                C013N00NN
	String 	A067 //RX-OTC Indicator Code              C001N00YN
	String 	A068 //Third-Party Restriction Code       C001N00YN
	String 	A069 //Maintenance Drug Code              C001N00YN
	String 	A070 //Dispensing Unit Code               C001N00YN
	String 	A071 //Unit-Dose/Unit-of-Use Package Code C001N00YN
	String 	A072 //Route of Administration            C002N00YY
	String 	A074 //Form Type Code                     C001N00YN
	String 	A075 //Dollar Rank Code                   C001N00YN
	String 	A076 //Rx Rank Code                       C001N00YN
	String 	A077 //Number System Character            C001N00YN
	String 	A078 //Secondary ID Format Code           C001N00YN
	String 	A079 //Secondary ID Number                C010N00NN
	String 	A089 //Multi-Source Code                  C001N00YN
	String 	A090 //Brand Name Code                    C001N00YN
	String 	A091 //Reimbursement Indicator            C001N00YN
	String 	A092 //Internal-External Code             C001N00YN
	String 	A093 //Single-Combination Code            C001N00YN
	String 	A094 //Storage Condition Code             C001N00YN
	String 	A095 //Limited Stability Code             C001N00YN
	
	static constraints = {
		//'A013'(size: 1..3)
	}
	static mapping = {
		//'A013' column: "Medi_A013", sqlType: "char", length: 1
	}
}

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeE extends RecordType implements Comparable {

    String 	E017 //Product Name                       C025N00NN
    String 	E042 //Product Name Extension             C035N00NN
    String 	E077 //Reserve-1                          C008N00NN
    String 	E085 //Allergy Pattern Code               N004N00NN
    String 	E089 //Reserve-2                          C002N00NN
    String 	E091 //PPG Indicator Code                 C001N00YN
    String 	E092 //HFPG Indicator Code                C001N00YN
    String 	E093 //Labeler Type Code                  C001N00YN
    String 	E094 //Pricing Spread Code                C001N00YN
    String 	E095 //Reserve-3                          C001N00NN

    static constraints = {
    }
    static mapping = {
    }
}

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeG extends RecordType implements Comparable {

    String 	G017 //Generic Product Identifier         C014N00NN
    String 	G031 //Reserve                            C005N00NN
    String 	G036 //GPI Generic Name                   C060N00NN

    static constraints = {
    }
    static mapping = {
    }
}

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeJ extends RecordType implements Comparable {

    String 	J017 //Manufacturer's (Labeler) Name      C030N00NN
    String 	J047 //Manufacturer's Abbreviated Name    C010N00NN
    String 	J057 //Product Description Abbreviation   C025N00NN
    String 	J082 //Drug Name Code                     C006N00NN
    String 	J088 //Generic Product Packaging Code     C008N00NN

    static constraints = {
    }
    static mapping = {
    }
}

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeL extends RecordType implements Comparable {

    String 	L017 //Reserve-1                          C023N00NN
    String 	L040 //Dosage Form                        C004N00YY
    String 	L044 //Package Size                       N008Y03NN
    String 	L052 //Package Size Unit of Measure       C002N00YN
    String 	L054 //Package Quantity                   N005N00NN
    String 	L059 //Repackage Code                     C001N00YN
    String 	L060 //Total Package Quantity             N012Y03NN
    String 	L072 //DESI Code                          C001N00YN
    String 	L073 //Package Description                C010N00NN
    String 	L083 //Reserve-2                          C006N00NN
    String 	L089 //Next-Smaller NDC Suffix Number     C002N00NN
    String 	L091 //Next-Larger NDC Suffix Number      C002N00NN
    String 	L093 //Innerpack Code                     C001N00YN
    String 	L094 //Clinic Pack Code                   C001N00YN
    String 	L095 //Reserve-3                          C001N00NN

    static constraints = {
    }
    static mapping = {
    }
}

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
class RecordTypeM extends RecordType implements Comparable {

    String 	M017 //Strength                           N013Y05NN
    String 	M030 //Strength Unit Measure              C011N00NN
    String 	M041 //Limited Dist Code                  C002N00YN
    String 	M043 //Extended AHFS Thera Class Code     N008N00NN
    String 	M051 //Inactive Date                      N008N00NN
    String 	M059 //Reserve                            C037N00NN

    static constraints = {
    }
    static mapping = {
    }
}


