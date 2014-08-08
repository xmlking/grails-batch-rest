package org.sumo.apiapp;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.messaging.core.MessageSendingOperations;

class WebLogAppender extends AppenderSkeleton implements Appender {

    // Injected from BootStrap.groovy
    static boolean appInitialized = false;

    String source;

    // Injected from BootStrap.groovy
    static MessageSendingOperations<String> brokerMessagingTemplate;

    @Override
    protected void append(LoggingEvent event) {
        if (appInitialized) {
            event.getNDC();
            event.getThreadName();
            // Get a copy of this thread's MDC.
            event.getMDCCopy();
            event.getLocationInformation();
            event.getRenderedMessage();
            event.getThrowableStrRep();

            String logStatement = getLayout().format(event);
            if(event.getLevel() == Level.ERROR) {
                brokerMessagingTemplate.convertAndSend("/topic/terminal/error", logStatement);
            }
            else {
                brokerMessagingTemplate.convertAndSend("/topic/terminal/log", logStatement);
            }
        }
    }

    /**
     * Set the source value for the logger (e.g. which application the logger belongs to)
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public void close() {
        //noop
        closed = true;
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
