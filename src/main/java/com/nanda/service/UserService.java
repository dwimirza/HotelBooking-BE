package com.nanda.service;

import com.nanda.model.Users;
import com.nanda.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Users> getAll() {
        return repository.findAll();
    }

    // public Optional<Users> getByCode(String code) {
    //     return repository.findById(code);
    // }

    public Users create(Users user) {
        return repository.save(user);
    }

    // public Users update(String code, Users user) {
    //     // ensure the code remains the same
    //     user.setUserCode(code);
    //     return repository.save(user);
    // }

    // public void delete(String code) {
    //     repository.deleteById(code);
    // }

    public List<Users> searchEmployees(String name, String zipcode) {
        if (name != null && zipcode != null) {
            // return repository.findByEmpNameContainingIgnoreCaseAndEmpZipCode(name, zipcode);
        } else if (name != null) {
            return repository.findByUserNameContainingIgnoreCase(name);
        } else if (zipcode != null) {
            // return repository.findByEmpZipCode(zipcode);
        }
        return repository.findAll();
    }
}
