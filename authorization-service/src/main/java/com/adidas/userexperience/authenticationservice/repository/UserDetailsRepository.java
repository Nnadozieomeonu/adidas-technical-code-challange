package com.adidas.userexperience.authenticationservice.repository;

import com.adidas.userexperience.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String name);
}
