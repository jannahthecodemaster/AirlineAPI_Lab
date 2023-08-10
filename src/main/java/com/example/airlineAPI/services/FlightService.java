package com.example.airlineAPI.services;

import com.example.airlineAPI.repositories.FlightRepository;
import com.example.airlineAPI.repositories.PassengerRepository;
import com.example.airlineAPI.models.Flight;
import com.example.airlineAPI.models.FlightDTO;
import com.example.airlineAPI.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    //Add details of a new flight
    public Flight addFlight(FlightDTO flightDTO) {
        Flight flight1 = new Flight(flightDTO.getDestination(), flightDTO.getCapacity(), flightDTO.getDepartureDate(), flightDTO.getDepartureTime());
        return flightRepository.save(flight1);
    }

    //  Display all available flights
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    //  Display details of a specific flight
    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).get();
    }

    // Book a passenger on to a flight


    //Cancel a flight
    public void cancelFlight(Long id){
        Flight flight = flightRepository.findById(id).get();
        flightRepository.delete(flight);
    }


    public Flight bookPassengerOnFlight(Long flightId, Passenger passenger) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);

        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();

            if (flight.getPassengers().size() < flight.getCapacity()) {
                flight.getPassengers().add(passenger);
                return flightRepository.save(flight);
            } else {
                throw new IllegalArgumentException("Flight is already full.");
            }
        } else {
            throw new IllegalArgumentException("Flight not found.");
        }
    }
}

