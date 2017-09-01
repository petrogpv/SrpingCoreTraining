package ua.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.epam.spring.core.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Petro_Gordeichuk on 8/30/2017.
 */

@Component
public class FileEventLogger implements EventLogger {

    @Value("${events.file:target/events_log.txt}")
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) {
        try {
//            FileUtils.writeStringToFile(file, event.toString() + "\n", "UTF-8");
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void init() throws IOException{
        file = new File(filename);
        if(file.exists() && !file.canWrite()){
            throw new IOException("Can`t write to file!");
        }
        else if(!file.exists()){
            file.createNewFile();
        }

    }
}
