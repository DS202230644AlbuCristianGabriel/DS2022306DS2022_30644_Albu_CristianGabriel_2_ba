package com.company.my_app;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MyAppApplication {
    public static void main(final String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }

}
