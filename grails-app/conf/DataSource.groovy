grails {
    mongo {
        host = "localhost"
        port = 27017
        username = ""
        password = ""
        options {
            autoConnectRetry = true
            connectTimeout = 300
        }
    }
}
environments {
    development {
        grails {
            mongo {
                //connectionString = "mongodb://localhost/sumo-dev"
                createDrop = "database"
                databaseName = "sumo-dev"
            }
        }
    }
    test {
        grails {
            mongo {
                createDrop = "database"
                databaseName = "sumo-test"
            }
        }
    }
    production {
        grails {
            mongo {
                createDrop = "none"
                databaseName = "sumo-prod"
            }
        }
    }
}