package com.niit.qlsv.dao.repository;

import com.niit.qlsv.dao.entities.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEnt, Long> {

    UserEnt findUserByUsername(String username);
    
}
