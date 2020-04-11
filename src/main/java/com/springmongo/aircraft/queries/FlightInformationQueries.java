package com.springmongo.aircraft.queries;

import com.springmongo.aircraft.domain.FlightInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(3)
public class FlightInformationQueries implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public FlightInformationQueries(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        getAllFlightInfo();
        getAllInternationalFlights();
        getAllOnTimeInternationalFlights();
        markAllToRomeFlightsAsDelayed();
    }

    //find all records of flight information
    private void getAllFlightInfo(){
        //Query query = Query.query();
        System.out.println("Get all flight information records::");
        List<FlightInformation> allFlights = mongoTemplate.findAll(FlightInformation.class);
        allFlights.stream().forEach(System.out::println);
        List<FlightInformation> flightsDepartureRome = allFlights.stream().filter(x -> x.getDepartureCity().equals("Rome")).collect(Collectors.toList());
        System.out.println("All Flights Departting from Rome:: ");
        flightsDepartureRome.stream().forEach(System.out::println);
    }

        //find all international flights
    private void getAllInternationalFlights(){
        Query query = Query.query(Criteria.where("type").is("International"));
        System.out.println("All International Flights::  ");
        List<FlightInformation> flightInformationsInternational = mongoTemplate.find(query, FlightInformation.class);
        flightInformationsInternational.stream().forEach(System.out::println);
    }

    //find all international flights with are not delayed

    private void getAllOnTimeInternationalFlights(){
        Query query = Query.query(Criteria.where("type").is("International").and("isDelayed").is(true));
        List<FlightInformation> internationalOnTimeFlights = mongoTemplate.find(query, FlightInformation.class);
        System.out.println("On Time:: International Flights::  ");
        internationalOnTimeFlights.stream().forEach(System.out::println);
    }

    //Update Queries:
    //Mark all flights to rome i.e. destination to rome as delayed
    public void markAllToRomeFlightsAsDelayed(){
        Query query = Query.query(Criteria.where("destination").is("Rome"));
        Update setDelayed = Update.update("isDelayed",true);

        mongoTemplate.updateMulti(query,setDelayed,FlightInformation.class);
        System.out.println("All flights to Rome updated with Delayed:: ");
        //check if all flights to rome has been updated::

        Query query1 = Query.query(Criteria.where("destination").is("Rome"));
        List<FlightInformation> toRomeFlightsDelayed = mongoTemplate.find(query1, FlightInformation.class);
        toRomeFlightsDelayed.stream().forEach(System.out::println);

    }
}
