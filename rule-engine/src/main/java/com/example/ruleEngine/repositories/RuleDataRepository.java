package com.example.ruleEngine.repositories;

import com.example.ruleEngine.domain.rules.RuleData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDataRepository extends CrudRepository<RuleData, String> {
}
