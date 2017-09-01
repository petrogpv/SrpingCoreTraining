package ua.epam.spring.core.loggers;


import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by Petro_Gordeichuk on 8/31/2017.
 */
@Component
public class CombinedEventLogger implements EventLogger{

    @Resource(name = "combinedLoggers")
    private final Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}
