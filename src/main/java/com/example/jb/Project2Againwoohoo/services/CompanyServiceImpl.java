package com.example.jb.Project2Againwoohoo.services;

import com.example.jb.Project2Againwoohoo.beans.Category;
import com.example.jb.Project2Againwoohoo.beans.Company;
import com.example.jb.Project2Againwoohoo.beans.Coupon;
import com.example.jb.Project2Againwoohoo.exceptions.ErrMsg;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class CompanyServiceImpl extends ClientManager implements CompanyService {
    private Company company;

    @Override
    public boolean login(String email, String password) throws Mistake {
        if (companyRepo.existsByEmail(email) && companyRepo.existsByPassword(password)) {
            company = companyRepo.findByEmail(email);
            return true;
        }
        throw new Mistake(ErrMsg.INCORRECT_LOGIN);
    }

    @Override
    public void addCoupon(Coupon coupon) throws Mistake {
        if(couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), company.getId())){
            throw new Mistake(ErrMsg.COUPON_ALREADY_EXIST);
        }

        coupon.setCompany(company);
        couponRepo.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponID) throws Mistake {
        if (!couponRepo.existsById(couponID)) {
            throw new Mistake(ErrMsg.COUPON_NOT_EXIST);
        }

        couponRepo.deleteById(couponID);
    }

    @Override
    public void updateCoupon(int couponID, Coupon coupon) throws Mistake {
        if (!couponRepo.existsById(couponID)) {
            throw new Mistake(ErrMsg.COUPON_NOT_EXIST);
        }

        couponRepo.saveAndFlush(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return company.getCoupons();
    }

    @Override
    public List<Coupon> getAllCouponsByCategory(Category category) {
        return couponRepo.findAllByCompanyIdAndCategory(company.getId(), category);

//        List<Coupon> coupons = new ArrayList<>();



//        for (Coupon coupon : company.getCoupons()) {
//            if (coupon.getCategory() == category) {
//                coupons.add(coupon);
//            }
//        }
//
//        return coupons;
    }

    @Override
    public List<Coupon> getAllCouponsByPrice(double price) {

        return couponRepo.findAllByCompanyIdAndPrice(company.getId(), price);
//        List<Coupon> coupons = new ArrayList<>();
//
//        for (Coupon coupon : company.getCoupons()) {
//            if (coupon.getPrice() < price) {
//                coupons.add(coupon);
//            }
//        }
//
//        return coupons;
    }

    @Override
    public Company getDetails() {
        return company;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws Mistake {
        return couponRepo.findById(couponID).orElseThrow(() -> new Mistake(ErrMsg.COUPON_NOT_EXIST));
    }
}
