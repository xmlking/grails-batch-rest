package org.sumo.apiapp

import grails.plugin.springsecurity.annotation.Secured
import org.codehaus.jackson.annotate.JsonProperty
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.annotation.SendToUser
import org.sumo.apiapp.stomp.StompExceptionHandler

import java.security.Principal

@Secured('permitAll')
@MessageMapping("/terminal")
class TerminalController implements StompExceptionHandler {

    def index() {
        render "index page"
    }

    @MessageMapping("/input/{containerId}")
    @SendToUser
    protected String execute(@DestinationVariable String containerId, String input,
                             Principal principal, SimpMessageHeaderAccessor headerAccessor) {
        def session = headerAccessor.sessionAttributes
        if (!session.command) {
            session.command = new StringBuilder()
        }
        session.command << input

        if (input == "\r") {
            if (principal.name != 'businessadmin') return "\r\nYou don't have access to terminal...\r\n"

            def result = new StringBuilder() << "\r\n"
            try {
                session.command.toString().execute().text.eachLine { line ->
                    result << line << "\r\n"
                }
            } catch (Exception e) {
                result << e.message + "\r\n"
            } finally {
                session.command.setLength(0)
            }
            return result
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

