import org.sumo.apiapp.security.*
class BootStrap {

    def init = { servletContext ->

        // Check whether the test data already exists.
        if (!User.count()) {
            createUsers()
        }
    }
    def destroy = {
    }

    private void createUsers() {
        def superAdminRole = new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true)
        def switchUserRole = new Role(authority: 'ROLE_SWITCH_USER').save(flush: true)
        def businessAdminRole = new Role(authority: 'ROLE_BUSINESS_ADMIN').save(flush: true)
        def itAdminRole = new Role(authority: 'ROLE_IT_ADMIN').save(flush: true)
        def dataAdminRole = new Role(authority: 'ROLE_DATA_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def superUser = new User(username: 'sumo', enabled: true, password: 'demo', firstName:'Sumanth', lastName:'Chintha', title:'IT Consultant', email:'sumo@demo.com' ).save(flush: true)
        def businessAdminUser = new User(username: 'businessadmin', enabled: true, password: 'businessadmin', firstName:'Sumanth', lastName:'Chintha', title:'Business User', email:'sumo@demo.com').save(flush: true)
        def dataAdminUser = new User(username: 'dataadmin', enabled: true, password: 'dataadmin', firstName:'Sumanth', lastName:'Chintha', title:'Data Admin', email:'sumo@demo.com').save(flush: true)
        def itAdminUser = new User(username: 'itadmin', enabled: true, password: 'itadmin', firstName:'Sumanth', lastName:'Chintha', title:'IT Admin', email:'sumo@demo.com').save(flush: true)
        def basicUser = new User(username: 'user', enabled: true, password: 'user', firstName:'Jeson', lastName:'Chang', title:'IT User', email:'jeson@demo.com').save(flush: true)

        UserRole.create superUser, superAdminRole, true
        UserRole.create superUser, userRole, true

        UserRole.create businessAdminUser, businessAdminRole, true
        UserRole.create businessAdminUser, userRole, true

        UserRole.create itAdminUser, itAdminRole, true
        UserRole.create itAdminUser, userRole, true

        UserRole.create dataAdminUser, dataAdminRole, true
        UserRole.create dataAdminUser, userRole, true

        UserRole.create basicUser, userRole, true
    }
}