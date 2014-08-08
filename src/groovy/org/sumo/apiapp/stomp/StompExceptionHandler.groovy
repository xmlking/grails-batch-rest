package org.sumo.apiapp.stomp

import org.springframework.messaging.MessagingException
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.simp.annotation.SendToUser

trait StompExceptionHandler {

    // failure handling pattern.

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleMessagingException(MessagingException exception) {
        log.error('handleMessagingException: ', exception)
        return exception.failedMessage;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleIllegalStateException(IllegalStateException exception) {
        log.error('handleIllegalStateException: ', exception)
        return exception.getMessage();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        log.error('handleException: ', exception)
        return exception.getMessage();
    }

}
