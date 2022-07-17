package com.mccayl.mccaylairlines.controller;

import com.mccayl.mccaylairlines.model.Passenger;
import com.mccayl.mccaylairlines.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/passengers")
public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getAll() {
        return passengerService.getAll();
    }

    @GetMapping("{id}")
    public Passenger getPassenger(@PathVariable Long id) {
        return passengerService.getById(id);
    }

    @PostMapping
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    @PutMapping("{id}")
    public Passenger updPassenger(@PathVariable Long id,
                                  @RequestBody Passenger passenger) {
        return passengerService.updPassenger(id, passenger);
    }

    @DeleteMapping("{id}")
    public void delPassenger(@PathVariable Long id) {
        passengerService.delPassenger(id);
    }
}
