package com.example.jb.Project2Againwoohoo.services;

import com.example.jb.Project2Againwoohoo.beans.Company;
import com.example.jb.Project2Againwoohoo.beans.Customer;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;

import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws Mistake;

    void updateCompany(int companyID, Company company) throws Mistake;

    void deleteCompany(int companyID) throws Mistake;

    List<Company> getAllCompanies();

    Company getOneCompany(int companyID) throws Mistake;

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int customerID) throws Mistake;

    void deleteCustomer(int customerID) throws Mistake;

    void updateCustomer(int customerID, Customer customer) throws Mistake;

    void addCustomer(Customer customer) throws Mistake;
}
