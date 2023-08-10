package com.example.airlineAPI.components;

import com.example.airlineAPI.repositories.FlightRepository;
import com.example.airlineAPI.repositories.PassengerRepository;
import com.example.airlineAPI.models.Flight;
import com.example.airlineAPI.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Add sample passengers and flights here
        List<Flight> flights = Arrays.asList(
                new Flight("Bali",100,"06,04,2023","12:00:00"),
                new Flight("Milan",200,"09,21,2023","16:35:00"),
                new Flight("Split",100,"14,12,2023","18:20:00"),
                new Flight("Geneva",200,"18,09,2023","13:55:00")
        );

        List<Passenger> passengers = Arrays.asList(
                new Passenger("pass1","01216556453"),
                new Passenger("pass2","07446448765"),
                new Passenger("pass3","07556445432"),
                new Passenger("pass4","07623323445")
        );

        for (Flight flight : flights) {
            Flight flightName = new Flight(flight.getDestination(),flight.getCapacity(), flight.getDepartureDate(), flight.getDepartureTime());
            flightRepository.save(flightName);
        }

        for (Passenger passenger : passengers) {
            Passenger passengerName = new Passenger(passenger.getName(), passenger.getPhoneNumber());
            passengerRepository.save(passengerName);
        }

    }


}
