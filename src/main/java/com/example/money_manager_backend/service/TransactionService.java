package com.example.money_manager_backend.service;

import com.example.money_manager_backend.model.Transaction;
import com.example.money_manager_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

        @Service
        public class TransactionService {
            @Autowired
            private TransactionRepository repository;

            public List<Transaction> getAll() {
                return repository.findAll();
            }

            public Transaction save(Transaction t) {
                if ("TRANSFER".equalsIgnoreCase(t.getType())) {
                    t.setDescription("Transfer [" + t.getDivision() + " → " + t.getToDivision() + "] " + t.getDescription());
                }
                return repository.save(t);
            }
        }

