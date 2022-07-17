package com.mccayl.mccaylairlines.repository;

import com.mccayl.mccaylairlines.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository <Passenger, Long> {

}
