package com.springmongo.aircraft;

import com.springmongo.aircraft.domain.FlightInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * In spring boot, a class that implements a command line runner
 * is executed after the application is bootstrapped
 *
 */
@Component
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public ApplicationRunner(MongoTemplate mongoTemplate){
            this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Application started....");
    }
}
