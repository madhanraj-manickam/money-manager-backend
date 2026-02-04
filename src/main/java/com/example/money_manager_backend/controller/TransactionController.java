package com.example.money_manager_backend.controller;

import com.example.money_manager_backend.model.Transaction;
import com.example.money_manager_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;


        @RestController
        @RequestMapping("/api/transactions")
        @CrossOrigin(origins = "*")
        public class TransactionController {
            @Autowired
            private TransactionService service;

            @GetMapping
            public List<Transaction> getAll() { return service.getAll(); }

            @PostMapping
            public Transaction create(@RequestBody Transaction t) { return service.save(t); }
        }



