package com.example.ruleEngine.repositories;

import com.example.ruleEngine.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);
}
