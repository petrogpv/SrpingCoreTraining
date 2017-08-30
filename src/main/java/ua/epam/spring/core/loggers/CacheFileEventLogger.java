package ua.epam.spring.core.loggers;

import ua.epam.spring.core.beans.Event;

import java.io.IOException;
import java.util.List;

/**
 * Created by Petro_Gordeichuk on 8/30/2017.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);
        if(cacheSize == cache.size());
    }
}
