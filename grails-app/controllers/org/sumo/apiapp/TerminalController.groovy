package org.sumo.apiapp

import grails.plugin.springsecurity.annotation.Secured
import org.codehaus.jackson.annotate.JsonProperty
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.messaging.simp.annotation.SubscribeMapping
import org.sumo.apiapp.stomp.StompExceptionHandler

import java.security.Principal

@Secured('permitAll')
@MessageMapping("/terminal")
class TerminalController implements StompExceptionHandler {

    def index() {
        render "index page"
    }

    @MessageMapping("/input")
    @SendToUser
    protected String echoKeystroke(String input) {
    //protected String echoKeystroke(ActionCommand input) {
        if(input == "\r") {
            return input + "\n"
        }
        return input
    }
}

class ActionCommand {
    @JsonProperty("name")
    String name
    @JsonProperty("message")
    String message
}