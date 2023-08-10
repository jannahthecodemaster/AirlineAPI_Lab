package com.example.airlineAPI.controllers;

import com.example.airlineAPI.repositories.PassengerRepository;
import com.example.airlineAPI.services.PassengerService;
import models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PassengerService passengerService;

    // Implement passenger-related routes: add passenger, get all passengers, get passenger by id
    @GetMapping //get all
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return new ResponseEntity<>(passengerService.findAllPassengers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}") //get by id
    public ResponseEntity<Optional<Passenger>> getPassenger(@PathVariable Long id){
        return new ResponseEntity(passengerService.findPassengerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger){
        Passenger savedPassenger = passengerService.addPassenger(passenger);
        return new ResponseEntity<>(savedPassenger , HttpStatus.CREATED);
    }

    }



