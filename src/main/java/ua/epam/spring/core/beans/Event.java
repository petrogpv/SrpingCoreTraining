package ua.epam.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Petro_Gordeichuk on 8/29/2017.
 */
public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        id = new Random().nextInt();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
