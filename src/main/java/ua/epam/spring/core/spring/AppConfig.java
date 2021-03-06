package ua.epam.spring.core.spring;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ua.epam.spring.core.App;
import ua.epam.spring.core.beans.Client;
import ua.epam.spring.core.beans.Event;
import ua.epam.spring.core.loggers.*;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Date newDate() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public Client client() {
        Client client = new Client();
        client.setId(environment.getRequiredProperty("id"));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }

}