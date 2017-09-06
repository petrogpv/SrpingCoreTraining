package ua.epam.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.beans.EventType;
import ua.epam.spring.core.loggers.EventLogger;
import ua.epam.spring.core.spring.AppConfig;
import ua.epam.spring.core.spring.LoggerConfig;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;

    @Resource(name = "defaultLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;


    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public App() {
    }

    //    public static void main(String[] args) {
////        ApplicationContext ctx =  new ClassPathXmlApplicationContext("config.xml");
////        App app = (App) ctx.getBean("app");
////        app.logEvent("Some message for 1");
////        app.logEvent("Some message for 2");
//
//        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
//        App app = (App) ctx.getBean("app");
//
//        Event event = ctx.getBean(Event.class);
//        app.logEvent(EventType.INFO, event, "Some event for 1");
//
//        event = ctx.getBean(Event.class);
//        app.logEvent(EventType.ERROR, event, "Some event for 2");
//
//        event = ctx.getBean(Event.class);
//        app.logEvent(null, event, "Some event for 3");
//
//        ctx.close();
//
//    }
public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(AppConfig.class, LoggerConfig.class);
    ctx.scan("ua.epam.spring.core");
    ctx.refresh();

    App app = (App) ctx.getBean("app");

    Client client = ctx.getBean(Client.class);
    System.out.println("Client says: " + client.getGreeting());

    Event event = ctx.getBean(Event.class);
    app.logEvent(EventType.INFO, event, "Some event for 1");

    event = ctx.getBean(Event.class);
    app.logEvent(EventType.ERROR, event, "Some event for 2");

    event = ctx.getBean(Event.class);
    app.logEvent(null, event, "Some event for 3");

    ctx.close();
}
    public void logEvent(EventType type, Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
