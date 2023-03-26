package com.example.ruleEngine.repositories;

import com.example.ruleEngine.domain.Audit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends CrudRepository<Audit, String> {
}
