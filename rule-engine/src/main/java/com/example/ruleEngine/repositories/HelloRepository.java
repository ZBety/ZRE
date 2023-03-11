package com.example.ruleEngine.repositories;


import com.example.ruleEngine.domain.Hello;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends CrudRepository<Hello, String> {

}
