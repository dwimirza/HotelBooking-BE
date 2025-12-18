package com.nanda.repository;

import com.nanda.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer>
{
    List<Users> findByUserNameContainingIgnoreCase(String userName);
    // List<Users> findByEmpZipCode(String empZipCode);
    // List<Users> findByEmpNameContainingIgnoreCaseAndEmpZipCode(String empName,String empZipCode
    // );
};