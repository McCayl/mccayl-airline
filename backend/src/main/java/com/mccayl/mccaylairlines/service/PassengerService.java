package com.mccayl.mccaylairlines.service;

import com.mccayl.mccaylairlines.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> getAll();
    Passenger getById(Long id);
    Passenger addPassenger(Passenger passenger);
    Passenger updPassenger(Long id, Passenger newPassenger);
    void delPassenger(Long id);
}
