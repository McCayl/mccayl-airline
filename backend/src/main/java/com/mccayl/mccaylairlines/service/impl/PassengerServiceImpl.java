package com.mccayl.mccaylairlines.service.impl;

import com.mccayl.mccaylairlines.exception.PassengerNotFoundException;
import com.mccayl.mccaylairlines.model.Passenger;
import com.mccayl.mccaylairlines.repository.PassengerRepository;
import com.mccayl.mccaylairlines.service.PassengerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @Override
    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.saveAndFlush(passenger);
    }

    @Override
    public Passenger updPassenger(Long id, Passenger newPassenger) {
        return passengerRepository.findById(id).map(passenger -> {
            passenger.setFirstname(newPassenger.getFirstname());
            passenger.setLastname(newPassenger.getLastname());
            passenger.setPassId(newPassenger.getPassId());
            passenger.setEmail(newPassenger.getEmail());
            return passengerRepository.saveAndFlush(passenger);
        }).orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @Override
    public void delPassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
