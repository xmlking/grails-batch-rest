## Spring Batch PoC

Grails Spring Batch, CORS enabled REST API, Spring Security Core/LDAP Demo

### Prerequisite 
```bash
# GVM - Tool for managing Groovy related frameworks  
curl -s get.gvmtool.net | bash
    
# install Grails 2.5.x
gvm install grails

# install MongoDB
brew install mongodb
```


### Getting Started

1. Install IDE

    latest [GGTS](http://grails.org/products/ggts) or [IntelliJ](http://www.jetbrains.com/idea/)
    
2. Checkout this Project

3. Integrate the project with your IDE ("--intellij--" or "--eclipse--")
    ```bash
    grails integrate-with --intellij
    ```

4. I am using forked version of [grails-spring-batch](https://github.com/xmlking/grails-spring-batch), slightly modified to make it work with Grails 2.4.x.

    we need to checkout this plugin first and build it locally so that plugin will be installed into local Maven cache.

    Run following command in `grails-spring-batch` project, to install the plugin into local Maven cache.

    ```bash
    maven-install
    ```

5. Then run following command in the project that is using this `grails-spring-batch` plugin.

   This will make the main project use local `grails-spring-batch` plugin instead of original plugin from grails repository.

    ```bash
    clean-all
    ```

6. To start the MongoDB 

    ```bash
     mongod --config data/mongod.conf
    ```

6. To start the server in dev mode.
    
    ```bash
    grails run-app
    ```
    
7. To load the database from flat file, run the spring batch job at `http://localhost:8080/apiApp/batch/springBatchJob/list`
 
    if you configured dev H2 database to persist to file e.g.,   `jdbc:h2:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE` you don't need to run the batch job every time you start the server. 
    
    It may be necessary to remove DB files (debDb.h2.db, debDb.lock.db) first from project folder to re-run the batch job and populate database. 

8. Authentication is configured to use LDAP/AD store first. if LDAP/AD is not available, it will fallback to Database user store.

    You can find test username/passwords in [BootStrap.groovy](grails-app/conf/BootStrap.groovy)


### REST URLs
    http://localhost:8080/apiApp/drugs.json?max=2
    http://localhost:8080/apiApp/drugs.json?max=2&offset=100
    http://localhost:8080/apiApp/drugs.json?max=2&offset=100&fields=ndc,id

    http://localhost:8080/apiApp/drugs/search?format=xml&labelerName=ONVATEC&productName=AKIN
    http://localhost:8080/apiApp/drugs/search?format=xml&labelerName=ONVATEC&productName=AKIN&max=2

    http://localhost:8080/apiApp/drugs/1.json
    http://localhost:8080/apiApp/drugs/1.json?fields=ndc,id
    http://localhost:8080/apiApp/drugs/1.json?fields=ndc,id,recordTypeE
    
    http://localhost:8080/apiApp/api/type/devices
    http://localhost:8080/apiApp/api/type/devices?lang=es
    http://localhost:8080/apiApp/api/type/devices?lang=en