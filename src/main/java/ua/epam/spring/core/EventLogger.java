package ua.epam.spring.core;

import ua.epam.spring.core.beans.Event;

import java.io.IOException;

/**
 * Created by Petro_Gordeichuk on 8/29/2017.
 */
public interface EventLogger {
    void logEvent(Event event) throws IOException;
}
