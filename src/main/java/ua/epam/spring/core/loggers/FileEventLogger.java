package ua.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import ua.epam.spring.core.EventLogger;
import ua.epam.spring.core.beans.Event;

import java.io.File;
import java.io.IOException;

/**
 * Created by Petro_Gordeichuk on 8/30/2017.
 */
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString(), "UTF-8");
    }

    public void init() throws IOException{
        this.file = new File(filename);
        if(file.canWrite()){
            throw new IOException("Can`t write to file!");
        }

    }
}
