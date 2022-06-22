package com.mccayl.mccaylairlines.service;

import com.mccayl.mccaylairlines.model.Flight;
import com.mccayl.mccaylairlines.model.User;

import java.util.List;

public interface FlightService {
    List<Flight> getAll();
    Flight getById(Long id);
    Flight addFlight(Flight flight);
    void delById(Long id);
    Flight updFlight(Long id, Flight newFlight);
    void addUser(Long id, User user);
    void delUser(Long id, User user);
}
