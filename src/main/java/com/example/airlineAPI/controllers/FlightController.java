package com.example.airlineAPI.controllers;

import com.example.airlineAPI.services.FlightService;
import com.example.airlineAPI.models.Flight;
import com.example.airlineAPI.models.FlightDTO;
import com.example.airlineAPI.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/flights")
    public class FlightController {

        @Autowired
        private FlightService flightService;

        // Implement flight-related routes: add flight, get all flights, get flight by id, book passenger, cancel flight


    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        return new ResponseEntity<>(flightService.findAllFlights(),HttpStatus.FOUND);
    }

    // Display details of a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlight(@PathVariable Long id){
        return new ResponseEntity(flightService.findFlightById(id),HttpStatus.FOUND);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> postFlight(@RequestBody FlightDTO flightDTO){
        Flight addFlight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(addFlight, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable Long id) {
        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

        @PostMapping("/{flightId}/book")
        public ResponseEntity<String> bookPassengerOnFlight(
                @PathVariable Long flightId, @RequestBody Passenger passenger) {
            try {
                Flight bookedFlight = flightService.bookPassengerOnFlight(flightId, passenger);
                return ResponseEntity.ok().body("Flight booked successfully");
            } catch (IllegalArgumentException exception) {
                return ResponseEntity.badRequest().body(exception.getMessage()); //researched, understand logic but syntax is tricky
            }
        }


    }


