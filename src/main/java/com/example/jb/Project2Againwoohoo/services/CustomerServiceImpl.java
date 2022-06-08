package com.example.jb.Project2Againwoohoo.services;

import com.example.jb.Project2Againwoohoo.beans.Category;
import com.example.jb.Project2Againwoohoo.beans.Coupon;
import com.example.jb.Project2Againwoohoo.beans.Customer;
import com.example.jb.Project2Againwoohoo.exceptions.ErrMsg;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@Scope("prototype")
public class CustomerServiceImpl extends ClientManager implements CustomerService {
    private Customer customer;

    @Override
    public boolean login(String email, String password) throws Mistake {
        if (customerRepo.existsByEmail(email) && customerRepo.existsByPassword(password)) {
            customer = customerRepo.findByEmail(email);
            return true;
        }
        throw new Mistake(ErrMsg.INCORRECT_LOGIN);
    }

    @Override
    public void buyCoupon(Coupon coupon) throws Mistake {
        if (coupon.getAmount() < 1) {
            throw new Mistake(ErrMsg.COUPON_RAN_OUT);
        }

        if (coupon.getEndDate().toLocalDate().isBefore(LocalDate.now())) {
            throw new Mistake(ErrMsg.COUPON_EXPIRED);
        }

        if(customerRepo.isCouponOwnedByCustomer(customer.getId(), coupon.getId()).intValue() == 1){
            throw new Mistake(ErrMsg.CUSTOMER_OWNS_COUPON);
        }

        customer.getCoupons().add(coupon);
        customerRepo.buyCoupon(customer.getId(), coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepo.saveAndFlush(coupon);
    }

    @Override
    public Set<Coupon> getAllCoupons() {
        return customer.getCoupons();
    }

    @Override
    public Set<Coupon> getAllCouponsByCategory(Category category) {
        Set<Coupon> coupons = new HashSet<>();

        for (Coupon coupon : customer.getCoupons()) {
            if (coupon.getCategory() == category) {
                coupons.add(coupon);
            }
        }

        return coupons;
    }

    @Override
    public Set<Coupon> getAllCouponsByPrice(double price) {
        Set<Coupon> coupons = new HashSet<>();

        for (Coupon coupon : customer.getCoupons()) {
            if (coupon.getPrice() < price) {
                coupons.add(coupon);
            }
        }

        return coupons;
    }

    @Override
    public Customer getDetails() {
        return customer;
    }
}
