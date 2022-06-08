package com.example.jb.Project2Againwoohoo.services;

import com.example.jb.Project2Againwoohoo.beans.Category;
import com.example.jb.Project2Againwoohoo.beans.Company;
import com.example.jb.Project2Againwoohoo.beans.Coupon;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws Mistake;

    void deleteCoupon(int couponID) throws Mistake;

    void updateCoupon(int couponID, Coupon coupon) throws Mistake;

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCouponsByCategory(Category category);

    List<Coupon> getAllCouponsByPrice(double price);

    Company getDetails();

    Coupon getOneCoupon(int couponID) throws Mistake;
}
