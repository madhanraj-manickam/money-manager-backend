package com.example.money_manager_backend.service;

import com.example.money_manager_backend.model.Transaction;
import com.example.money_manager_backend.model.User;
import com.example.money_manager_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getByUser(User user) {
        return repository.findAll().stream()
                .filter(t -> t.getUser() != null && t.getUser().getId().equals(user.getId()))
                .toList();
    }

    public Transaction save(Transaction t, User user) {
        t.setUser(user);
        if ("TRANSFER".equalsIgnoreCase(t.getType())) {
            t.setDescription("Transfer [" + t.getDivision() + " → " + t.getToDivision() + "] " + t.getDescription());
        }
        return repository.save(t);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Transaction update(Long id, Transaction details) {
        Transaction t = repository.findById(id).orElseThrow();
        t.setAmount(details.getAmount());
        t.setDescription(details.getDescription());
        t.setCategory(details.getCategory());
        t.setType(details.getType());
        return repository.save(t);
    }
}