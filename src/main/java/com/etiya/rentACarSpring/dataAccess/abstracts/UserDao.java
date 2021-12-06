package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User getByEmail(String eMail);
}
