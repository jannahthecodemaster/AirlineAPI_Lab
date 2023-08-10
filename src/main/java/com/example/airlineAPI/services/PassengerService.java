package com.example.airlineAPI.services;

import com.example.airlineAPI.repositories.PassengerRepository;
import com.example.airlineAPI.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

   // Display details of all passengers
    public List<Passenger> findAllPassengers(){
        return passengerRepository.findAll();
    }
   // Display details of a specific passenger
    public Passenger findPassengerById(Long id){
        return passengerRepository.findById(id).get();
    }

//  Add a new passenger
public Passenger addPassenger(Passenger passenger) {
     Passenger passenger1 = new Passenger(passenger.getName(), passenger.getPhoneNumber());
    return passengerRepository.save(passenger1);
}
}
