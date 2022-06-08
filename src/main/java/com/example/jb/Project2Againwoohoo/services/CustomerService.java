package com.example.jb.Project2Againwoohoo.services;

import com.example.jb.Project2Againwoohoo.beans.Category;
import com.example.jb.Project2Againwoohoo.beans.Coupon;
import com.example.jb.Project2Againwoohoo.beans.Customer;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;

import java.util.Set;

public interface CustomerService {
    void buyCoupon(Coupon coupon) throws Mistake;

    Set<Coupon> getAllCoupons();

    Set<Coupon> getAllCouponsByCategory(Category category);

    Set<Coupon> getAllCouponsByPrice(double price);

    Customer getDetails();
}
