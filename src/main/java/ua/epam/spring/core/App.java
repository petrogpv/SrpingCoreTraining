package ua.epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.beans.Client;

import java.io.IOException;

public class App {
    private Client client ;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("config.xml");
        App app = (App) ctx.getBean("app");
//        app.logEvent("Some message for 1");
//        app.logEvent("Some message for 2");
    }
    public void logEvent(Event event) throws IOException {
//        String message = event.toString().replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(event);
    }
}
