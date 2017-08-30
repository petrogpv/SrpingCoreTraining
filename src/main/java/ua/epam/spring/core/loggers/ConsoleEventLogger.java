package ua.epam.spring.core.loggers;

import ua.epam.spring.core.EventLogger;
import ua.epam.spring.core.beans.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event){
        System.out.println(event.toString());
    }
}
