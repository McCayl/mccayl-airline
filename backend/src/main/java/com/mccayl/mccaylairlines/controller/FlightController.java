package com.mccayl.mccaylairlines.controller;

import com.mccayl.mccaylairlines.model.Flight;
import com.mccayl.mccaylairlines.model.User;
import com.mccayl.mccaylairlines.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAll() {
        return flightService.getAll();
    }

    @GetMapping("{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.getById(id);
    }

    @PostMapping
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @DeleteMapping("{id}")
    public void delFlight(@PathVariable Long id) {
        flightService.delById(id);
    }

    @PutMapping("{id}")
    public Flight updFlight(@PathVariable Long id,
                            @RequestBody Flight flight) {
        return flightService.updFlight(id, flight);
    }

    @PostMapping("{id}/users")
    public void addUser(@PathVariable Long id,
                        @RequestBody User user) {
        flightService.addUser(id, user);
    }

    @DeleteMapping("{id}/users")
    public void delUser(@PathVariable Long id,
                        @RequestBody User user) {
        flightService.delUser(id, user);
    }
}
