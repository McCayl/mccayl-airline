package com.mccayl.mccaylairlines.repository;

import com.mccayl.mccaylairlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

}
