package com.example.ruleEngine.service;

import com.example.ruleEngine.domain.Coupons;
import com.example.ruleEngine.domain.UserCoupons;
import com.example.ruleEngine.repositories.CouponsRepository;
import com.example.ruleEngine.repositories.UserCouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Action {

    @Autowired
    private UserCouponsRepository ucRepo;

    @Autowired
    private CouponsRepository repository;

    public void grantCoupons(String username, List<String> types) {
        List<UserCoupons> userCouponsList = new ArrayList<>();
        List<Coupons> coupons = repository.findAllByNameIn(types);
        for (Coupons item : coupons) {
            UserCoupons userCoupons = new UserCoupons();
            userCoupons.setUsername(username);
            userCoupons.setType(item.getType());
            userCoupons.setAmount(item.getAmount());
            userCoupons.setAmountLevel(item.getAmountLevel());
            userCoupons.setName(item.getName());
            userCoupons.setCreateTime(System.currentTimeMillis());
            userCouponsList.add(userCoupons);
        }
        ucRepo.saveAll(userCouponsList);
        System.out.println("优惠价发放完成");
    }

    public void sendEmail(Object data) {
        System.out.println("优惠邮件发送完成");
    }

    public void addPoints(String username) {
        System.out.println("积分发放完成");
    }
}
