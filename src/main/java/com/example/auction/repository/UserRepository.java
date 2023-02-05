package com.example.auction.repository;

import com.example.auction.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getByEmail(String email);
    boolean existsByEmail(String email);
}
