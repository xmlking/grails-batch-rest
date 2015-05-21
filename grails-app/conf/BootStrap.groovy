import grails.converters.JSON;
import org.sumo.apiapp.security.*
import org.sumo.apiapp.*
import org.bson.types.ObjectId
import groovy.time.TimeCategory

class BootStrap {

    def brokerMessagingTemplate
    def customMarshallerRegistrar

    def init = { servletContext ->
        // for WebLogAppender
        WebLogAppender.brokerMessagingTemplate = brokerMessagingTemplate
        WebLogAppender.appInitialized = true

        // custom Marshaller
        customMarshallerRegistrar.registerMarshallers()

        // MongoDB id to string
        JSON.registerObjectMarshaller(ObjectId) {
            return it.toStringMongod()
        }

        // Check whether the test data already exists.
        if (!User.count()) {
            createUsers()
        }
        if (!Provider.count()) {
            createProviders()
        }
    }
    def destroy = {
    }

    private void createUsers() {
        def superAdminRole =    new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true)
        def switchUserRole =    new Role(authority: 'ROLE_SWITCH_USER').save(flush: true)
        def businessAdminRole = new Role(authority: 'ROLE_BUSINESS_ADMIN').save(flush: true)
        def itAdminRole =       new Role(authority: 'ROLE_IT_ADMIN').save(flush: true)
        def dataAdminRole =     new Role(authority: 'ROLE_DATA_ADMIN').save(flush: true)
        def userRole =          new Role(authority: 'ROLE_USER').save(flush: true)

        def superUser =         new User(username: 'sumo',          enabled: true, password: 'sumodemo',    firstName:'Sumanth', lastName:'Chintha', email:'sumo@demo.com',   authorities:[superAdminRole, userRole] ).save(flush: true)
        def businessAdminUser = new User(username: 'businessadmin', enabled: true, password: 'sumodemo',    firstName:'Sumanth', lastName:'Chintha', email:'badmin@demo.com', authorities:[businessAdminRole, userRole] ).save(flush: true)
        def dataAdminUser =     new User(username: 'dataadmin',     enabled: true, password: 'sumodemo',    firstName:'Sumanth', lastName:'Chintha', email:'dadmin@demo.com', authorities:[dataAdminRole, userRole] ).save(flush: true)
        def itAdminUser =       new User(username: 'itadmin',       enabled: true, password: 'sumodemo',    firstName:'Sumanth', lastName:'Chintha', email:'iadmin@demo.com', authorities:[itAdminRole, userRole] ).save(flush: true)
        def basicUser =         new User(username: 'user',          enabled: true, password: 'sumodemo',    firstName:'Jeson',   lastName:'Chang',   email:'jeson@demo.com',  authorities:[userRole] ).save(flush: true)
    }


    private void createProviders() {

        Provider.list().each {
                it.delete(flush: true)
            }

        Specialty.list().each {
                it.delete(flush: true)
            }

        Address.list().each {
            it.delete(flush: true)
        }

        Date fifty, effective, future, now = new Date()
        use(TimeCategory) {
            fifty = now - 50.years
            effective = now - 1.week - 4.days + 2.hours - 3.seconds
            future = now + 50.years
        }

        Device phone1 = new Device(type:DeviceType.PHONE,value:'999-111-2222')
        Device email1 = new Device(type:DeviceType.EMAIL,value:'abc@mycom.com')
        SocialMedia twitter1 = new SocialMedia(type:SocialMediaType.TWITTER,value:'doc123')
        Address address1 = new Address(street:'123 king st',city:'Irvine', state:'CA',zip:'23432')
        Address address2 = new Address(street:'444 Wood Rd',city:'Riverside', state:'CA',zip:'44433')

        Contact contact1 = new Contact(address:address1,devices:[phone1])
        Contact contact2 = new Contact(address:address2,devices:[email1])

        Specialty pediatrics = new Specialty(name:'Pediatrics', description: 'Pediatrics')
        Specialty adolescent = new Specialty(name:'Adolescent Medicine', description: 'Adolescent Medicine', parent:pediatrics)
        Specialty castroenterology = new Specialty(name:'Pediatric Gastroenterology', description: 'Pediatric Gastroenterology', parent:pediatrics)
        Specialty endocrinology = new Specialty(name:'Pediatric Endocrinology', description: 'Pediatric Endocrinology', parent:pediatrics)

        Specialty dermatology = new Specialty(name:'Dermatology', description: 'Dermatology')
        Specialty dermatopathology = new Specialty(name:'Dermatopathology', description: 'Dermatopathology', parent:dermatology)
        Specialty proceduralDermatology = new Specialty(name:'Procedural Dermatology', description: 'Procedural Dermatology', parent:dermatology)

        Provider provider1 = new Provider(firstName:'Mark',lastName:'Twain',gender: GenderType.MALE,
                dateOfBirth:fifty,effectiveDate:effective, cancellationDate:future,
                npi:'111222333',image:'https://www.google.com/images/srpr/logo11w.png',rating:4,
                type:ProviderType.PHYSICIAN,socialMedias:[twitter1])

        provider1.addToSpecialties(proceduralDermatology)
        provider1.addToSpecialties(castroenterology)
        provider1.addToSpecialties(adolescent)
        provider1.addToContacts(contact1)
        provider1.addToContacts(contact2)

        pediatrics.save(flush: true, failOnError: true);
        adolescent.save(flush: true, failOnError: true);
        castroenterology.save(flush: true, failOnError: true);
        endocrinology.save(flush: true, failOnError: true);
        dermatology.save(flush: true, failOnError: true);
        dermatopathology.save(flush: true, failOnError: true);
        proceduralDermatology.save(flush: true, failOnError: true);

        provider1.save(flush: true, failOnError: true);
    }
}