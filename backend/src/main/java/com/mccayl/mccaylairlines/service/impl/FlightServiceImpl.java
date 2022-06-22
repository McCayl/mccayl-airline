package com.mccayl.mccaylairlines.service.impl;

import com.mccayl.mccaylairlines.exception.FlightNotFoundException;
import com.mccayl.mccaylairlines.model.Flight;
import com.mccayl.mccaylairlines.model.User;
import com.mccayl.mccaylairlines.repository.FlightRepository;
import com.mccayl.mccaylairlines.repository.UserRepository;
import com.mccayl.mccaylairlines.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepo;
    private UserRepository userRepo;

    public FlightServiceImpl(FlightRepository flightRepo,
                             UserRepository userRepo) {
        this.flightRepo = flightRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Flight> getAll() {
        return flightRepo.findAll();
    }

    @Override
    public Flight getById(Long id) {
        return flightRepo.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepo.saveAndFlush(flight);
    }

    @Override
    public void delById(Long id) {
        flightRepo.deleteById(id);
    }

    @Override
    public Flight updFlight(Long id, Flight newFlight) {
        return flightRepo.findById(id).map(flight -> {
            flight.setFromCity(newFlight.getFromCity());
            flight.setToCity(newFlight.getToCity());
            flight.setDeparture(newFlight.getDeparture());
            flight.setArrival(newFlight.getArrival());
            return flightRepo.saveAndFlush(flight);
        }).orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public void addUser(Long id, User user) {
        flightRepo.findById(id).map(flight -> {
            flight.addUser(user);
            userRepo.saveAndFlush(user);
            return flightRepo.saveAndFlush(flight);
        }).orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public void delUser(Long id, User user) {
        flightRepo.findById(id).map(flight -> {
            flight.delUser(user);
            userRepo.saveAndFlush(user);
            return flightRepo.saveAndFlush(flight);
        }).orElseThrow(() -> new FlightNotFoundException(id));
    }
}
