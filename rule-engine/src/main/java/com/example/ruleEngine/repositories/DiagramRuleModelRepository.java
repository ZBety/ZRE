package com.example.ruleEngine.repositories;

import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagramRuleModelRepository extends CrudRepository<DiagramRuleModel, String> {
}