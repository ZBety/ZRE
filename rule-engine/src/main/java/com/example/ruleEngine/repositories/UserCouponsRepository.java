package com.example.ruleEngine.repositories;


import com.example.ruleEngine.domain.Coupons;
import com.example.ruleEngine.domain.UserCoupons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCouponsRepository extends CrudRepository<UserCoupons, String> {
    List<UserCoupons> findAllByUsername( String username);
}
