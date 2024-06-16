package com.VasilijeISA.ISA2023.repository;

import com.VasilijeISA.ISA2023.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findByActivationToken(String token);
}
