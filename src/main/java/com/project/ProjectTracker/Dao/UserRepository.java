package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    <S extends User> Optional<S> findByUsername(String username);
}