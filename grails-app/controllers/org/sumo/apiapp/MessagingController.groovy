package org.sumo.apiapp

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping

import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.messaging.simp.annotation.SubscribeMapping
import org.sumo.apiapp.stomp.StompExceptionHandler

import java.security.Principal

@Secured('permitAll')
@MessageMapping("/chat")
class MessagingController implements StompExceptionHandler {

    def messagingService

    def index() {
        //log.error "this is error"
        //throw new IllegalStateException("test IllegalStateException")
        render "index page"
    }

    // broadcast pattern. Join/Leave Announcements, send messages
    @MessageMapping("/join")
    @SendTo("/topic/chat/announcements.join")
    protected String join(String message, Principal principal) {
        log.debug "Join Message: ${message} from ${principal.name}"
        messagingService.addUser(principal.name)
        Date now = Calendar.getInstance().getTime()
        return "${principal.name} joined the chatroom at ${now}. Message: ${message}!"
    }

    @MessageMapping("/leave")
    @SendTo("/topic/chat/announcements.left")
    protected String leave(String message, Principal principal) {
        log.debug "Goodbye Message: ${message} from ${principal.name}"
        messagingService.removeUser(principal.name)
        Date now = Calendar.getInstance().getTime()
        return "${principal.name} left the chatroom at ${now}. Message: ${message}!"
    }

    @MessageMapping("/messages") //without @SendTo or @SendToUser, Default response address: /topic/chat/messages
    protected String sendToAll(String message, Principal principal) {
        log.debug "message: [${message}] from [${principal.name}]"
        return "[${principal.name}]: ${message}"
    }

    // reply only to sender pattern.
    @MessageMapping("/self")
    @SendToUser  //SendToUser's Default response address: /user/queue/chat/self
    protected String sendToUser(String message,  Principal principal) {
        log.debug "Hello [${principal.name}] you send: ${message}"
        return "Hello [${principal.name}] you send: ${message}"
    }

    /**
     * request-reply pattern.
     * @SubscribeMapping method is sent as a message directly back to the connected client and does not pass through the broker
     */
    @SubscribeMapping("/rooms/{id}")
    protected Set<String> getChatRooms(@DestinationVariable String id) {
        log.debug "id: ${id}"
        return messagingService.rooms
    }

    // request-reply pattern.
    @SubscribeMapping("/users/{room}")
    protected Set<String> getActiveUsers(@DestinationVariable String room) {
        log.debug "serving users for room : ${room}"
        return messagingService.getUsers(room)
    }

}
