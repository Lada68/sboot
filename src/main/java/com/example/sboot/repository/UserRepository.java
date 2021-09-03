package com.example.sboot.repository;

import com.example.sboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Object findByUsername(String username);

    @Override
    Optional<User> findById(Integer integer);

    User findById(int id);
}
