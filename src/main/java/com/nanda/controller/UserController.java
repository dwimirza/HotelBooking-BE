package com.nanda.controller;

import com.nanda.model.Users;
import com.nanda.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Users> getAll() {
        return service.getAll();
    }

    // @GetMapping("/{code}")
    // public Users getByCode(@PathVariable String code) {
    //     return service.getByCode(code)
    //             .orElseThrow(() -> new RuntimeException("Employee not found"));
    // }

    @PostMapping
    public Users create(@RequestBody Users user) {
        return service.create(user);
    }

    // @PutMapping("/{code}")
    // public Users update(@PathVariable String code, @RequestBody Users emp) {
    //     return service.update(code, emp);
    // }

    // @DeleteMapping("/{code}")
    // public String delete(@PathVariable String code) {
    //     service.delete(code);
    //     return "Deleted: " + code;
    // }

    @GetMapping("/search")
    public List<Users> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String zipcode) {
        return service.searchEmployees(name, zipcode);
    }
}
