package ua.epam.spring.core.loggers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petro_Gordeichuk on 8/30/2017.
 */
@Component
public class CacheFileEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger() {
    }

        public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event)  {
        cache.add(event);
        if(cacheSize == cache.size()){
            writeEventsFromCache();
            cache.clear();
        };
    }

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }
    @PreDestroy
    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }


}
