package com.example.ruleEngine.repositories;


import com.example.ruleEngine.domain.Coupons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsRepository extends CrudRepository<Coupons, String> {
    List<Coupons> findAllByNameIn(List<String> names);
}
