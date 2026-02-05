package com.example.money_manager_backend.controller;

import com.example.money_manager_backend.model.Transaction;
import com.example.money_manager_backend.model.User;
import com.example.money_manager_backend.service.TransactionService;
import com.example.money_manager_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    public List<Transaction> getAll(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return service.getByUser(user);
    }

    @PostMapping("/user/{username}")
    public Transaction create(@RequestBody Transaction t, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword("password");
            user = userRepository.save(user);
        }
        return service.save(t, user);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction t) {
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}