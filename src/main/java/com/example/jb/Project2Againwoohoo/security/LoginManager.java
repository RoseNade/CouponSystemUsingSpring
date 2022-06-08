package com.example.jb.Project2Againwoohoo.security;


import com.example.jb.Project2Againwoohoo.beans.ClientType;
import com.example.jb.Project2Againwoohoo.exceptions.Mistake;
import com.example.jb.Project2Againwoohoo.services.AdminServiceImpl;
import com.example.jb.Project2Againwoohoo.services.ClientManager;
import com.example.jb.Project2Againwoohoo.services.CompanyServiceImpl;
import com.example.jb.Project2Againwoohoo.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {
    @Autowired
    private ApplicationContext applicationContext;

    public ClientManager login(String email, String password, ClientType clientType) throws Mistake {
        switch(clientType) {
            case ADMIN:
                AdminServiceImpl admin = applicationContext.getBean(AdminServiceImpl.class);
                if (admin.login(email, password)) {
                    return admin;
                }

            case COMPANY:
                CompanyServiceImpl company = applicationContext.getBean(CompanyServiceImpl.class);
                if (company.login(email, password)) {
                    return company;
                }

            case CUSTOMER:
                CustomerServiceImpl customer = applicationContext.getBean(CustomerServiceImpl.class);
                if(customer.login(email, password)){
                    return customer;
                }
        }
        return null;
    }
}
