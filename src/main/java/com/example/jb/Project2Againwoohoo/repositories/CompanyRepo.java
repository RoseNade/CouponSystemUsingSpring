package com.example.jb.Project2Againwoohoo.repositories;

import com.example.jb.Project2Againwoohoo.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    Company findByEmail(String email);
}
