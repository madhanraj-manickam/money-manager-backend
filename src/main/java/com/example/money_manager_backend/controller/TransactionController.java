package com.example.money_manager_backend.controller;

import com.example.money_manager_backend.model.Transaction;
import com.example.money_manager_backend.model.User;
import com.example.money_manager_backend.service.TransactionService;
import com.example.money_manager_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(
        origins = "https://money-manager-frontend-xi.vercel.app",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private UserRepository userRepository;

    // --- AUTHENTICATION ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userRepository.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return ResponseEntity.status(400).body("User already exists");
        }
        return ResponseEntity.ok(userRepository.save(newUser));
    }

    // --- TRANSACTIONS ---
    @GetMapping("/transactions/user/{username}")
    public List<Transaction> getAll(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return service.getByUser(user);
    }

    @PostMapping("/transactions/user/{username}")
    public ResponseEntity<?> create(@RequestBody Transaction t, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok(service.save(t, user));
    }

    @PutMapping("/transactions/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction t) {
        return service.update(id, t);
    }

    @DeleteMapping("/transactions/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/transactions/transfer/{username}")
    public ResponseEntity<?> transfer(@RequestBody Transaction t, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return ResponseEntity.status(404).body("User not found");

        // Creates the "Out" entry
        Transaction source = new Transaction();
        source.setAmount(t.getAmount());
        source.setDescription("Transfer to " + t.getToDivision() + ": " + t.getDescription());
        source.setType("EXPENSE");
        source.setDivision(t.getDivision());
        service.save(source, user);

        // Creates the "In" entry
        Transaction target = new Transaction();
        target.setAmount(t.getAmount());
        target.setDescription("Transfer from " + t.getDivision() + ": " + t.getDescription());
        target.setType("INCOME");
        target.setDivision(t.getToDivision());
        service.save(target, user);

        return ResponseEntity.ok("Transfer successful");
    }
    }
