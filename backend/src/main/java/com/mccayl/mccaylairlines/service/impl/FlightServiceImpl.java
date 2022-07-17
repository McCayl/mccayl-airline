package com.mccayl.mccaylairlines.service.impl;

import com.mccayl.mccaylairlines.exception.FlightNotFoundException;
import com.mccayl.mccaylairlines.model.Flight;
import com.mccayl.mccaylairlines.model.Passenger;
import com.mccayl.mccaylairlines.repository.FlightRepository;
import com.mccayl.mccaylairlines.repository.PassengerRepository;
import com.mccayl.mccaylairlines.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;

    public FlightServiceImpl(FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }

    @Override
    public void delById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Flight updFlight(Long id, Flight newFlight) {
        return flightRepository.findById(id).map(flight -> {
            flight.setFromCity(newFlight.getFromCity());
            flight.setToCity(newFlight.getToCity());
            flight.setDeparture(newFlight.getDeparture());
            flight.setArrival(newFlight.getArrival());
            return flightRepository.saveAndFlush(flight);
        }).orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public void addPassenger(Long id, Passenger passenger) {
        flightRepository.findById(id).map(flight -> {
            flight.addPassenger(passenger);
            passengerRepository.saveAndFlush(passenger);
            return flightRepository.saveAndFlush(flight);
        }).orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public void delPassenger(Long id, Passenger passenger) {
        flightRepository.findById(id).map(flight -> {
            flight.delPassenger(passenger);
            passengerRepository.saveAndFlush(passenger);
            return flightRepository.saveAndFlush(flight);
        }).orElseThrow(() -> new FlightNotFoundException(id));
    }
}
