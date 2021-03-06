package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    <S extends User> Optional<S> findByUsername(String username);

    Optional<User> findByUsernameAndVerificationCode(String username, String otp);

    Page<User> findAll(Pageable pageable);

    Optional<List<User>> findByUsernameStartsWith(String username);
    long countByUsernameStartingWith(String username);

//    Optional<User> findByUsername(String username);
}