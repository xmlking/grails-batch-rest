package org.sumo.apiapp

import grails.async.Promise
import static grails.async.Promises.*
import grails.transaction.NotTransactional
import grails.transaction.Transactional
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled


@Transactional
@EnableScheduling
class MessagingService {

    def brokerMessagingTemplate
    def users= ['All', 'user1', 'user2'] as Set
    def rooms  = ['Developers','Hackers', 'Stockers'] as Set
    def symbols = ['AAPL','YHOO', 'GOOG'] as Set

    @NotTransactional
    //@Scheduled(fixedDelay=60000L)
    @Scheduled(fixedRate=60000L)
    void sendGreetingOnceMinute() {
        log.debug "sending Scheduled greeting"
        brokerMessagingTemplate.convertAndSend "/topic/self", "hello from service!"
    }

    @NotTransactional
    Promise stock(String ticker = 'GOOG') {
        task {
            def url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=${ticker}&f=nsl1op&e=.csv")
            Double price = url.text.split(',')[-1] as Double
            return [ticker: ticker, price: price]
        }
    }

    //Sending updates to clients periodically
    @Scheduled(cron="*/60 * * * * ?")
    @NotTransactional
    public void sendQuotes() {

        symbols.collect this.&stock each {
            log.debug it.class
            it.then {
                log.debug it
                brokerMessagingTemplate.convertAndSend  "/topic/price.stock." + it.ticker, it.price
            }
        }
    }

    public Set<String> getRooms() {
        rooms
    }
    @NotTransactional
    public void addUser(String user) {
        users.add(user)
    }
    @NotTransactional
    public void removeUser(String user) {
        users.remove(user)
    }

    public Set<String> getUsers(String room) {
        log.debug "serving users for room: ${room}"
        users
    }

}

