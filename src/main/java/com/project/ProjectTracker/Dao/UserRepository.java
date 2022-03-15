package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    <S extends User> Optional<S> findByUsername(String username);

    Optional<User> findByUsernameAndVerificationCode(String username, String otp);
}