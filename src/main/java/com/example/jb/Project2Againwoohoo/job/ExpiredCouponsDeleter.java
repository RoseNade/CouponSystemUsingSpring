package com.example.jb.Project2Againwoohoo.job;

import com.example.jb.Project2Againwoohoo.repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ExpiredCouponsDeleter {
    @Autowired
    private CouponRepo couponRepo;

    @Scheduled(fixedRate = 1_000 * 5)
    public void run(){
        couponRepo.deleteExpiredCoupons();
        System.out.println("Coupons deleted successfully");
    }
}
