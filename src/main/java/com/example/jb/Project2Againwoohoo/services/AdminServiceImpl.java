package com.example.jb.Project2Againwoohoo.services;

import com.example.jb.Project2Againwoohoo.beans.Company;
import com.example.jb.Project2Againwoohoo.beans.Customer;
import com.example.jb.Project2Againwoohoo.exceptions.ErrMsg;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientManager implements AdminService {

    @Override
    public void addCustomer(Customer customer) throws Mistake {
        if(customerRepo.existsByEmail(customer.getEmail())){
            throw new Mistake(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }
        customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(int customerID) throws Mistake {
        if(!customerRepo.existsById(customerID)){
            throw new Mistake(ErrMsg.CUSTOMER_NOT_EXIST);
        }
        customerRepo.deleteById(customerID);
    }

    @Override
    public void updateCustomer(int customerID, Customer customer) throws Mistake {
        if(!customerRepo.existsById(customerID)){
            throw new Mistake(ErrMsg.CUSTOMER_NOT_EXIST);
        }

        customer.setId(customerID);
        customerRepo.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerID) throws Mistake {
        return customerRepo.findById(customerID).orElseThrow(() -> new Mistake(ErrMsg.CUSTOMER_NOT_EXIST));
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    @Override
    public void addCompany(Company company) throws Mistake {
        if (companyRepo.existsById(company.getId())) {
            throw new Mistake(ErrMsg.COMPANY_ALREADY_EXISTS);
        }

        companyRepo.save(company);
    }

    @Override
    public void updateCompany(int companyID, Company company) throws Mistake {
        if (!companyRepo.existsById(company.getId())) {
            throw new Mistake(ErrMsg.COMPANY_NOT_EXIST);
        }

        company.setId(companyID);
        companyRepo.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyID) throws Mistake {
        if (!companyRepo.existsById(companyID)) {
            throw new Mistake(ErrMsg.COMPANY_NOT_EXIST);
        }

        companyRepo.deleteById(companyID);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getOneCompany(int companyID) throws Mistake {
        return companyRepo.findById(companyID).orElseThrow(() -> new Mistake(ErrMsg.COMPANY_NOT_EXIST));
    }
}
