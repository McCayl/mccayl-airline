package com.mccayl.mccaylairlines.repository;

import com.mccayl.mccaylairlines.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByName(String name);
}
