package com.group3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.model.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer>
{
    List<Users> findByUserNameContainingIgnoreCase(String userName);
    // List<Users> findByEmpZipCode(String empZipCode);
    // List<Users> findByEmpNameContainingIgnoreCaseAndEmpZipCode(String empName,String empZipCode
    // );
};