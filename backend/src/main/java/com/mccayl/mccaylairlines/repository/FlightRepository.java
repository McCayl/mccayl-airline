package com.mccayl.mccaylairlines.repository;

import com.mccayl.mccaylairlines.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository <Flight, Long> {

}
