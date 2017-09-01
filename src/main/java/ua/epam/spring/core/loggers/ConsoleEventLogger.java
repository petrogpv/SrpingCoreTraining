package ua.epam.spring.core.loggers;

import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;

@Component
public class ConsoleEventLogger implements EventLogger {
    public ConsoleEventLogger() {
    }

    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
