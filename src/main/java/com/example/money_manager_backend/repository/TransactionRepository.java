package com.example.money_manager_backend.repository;
import com.example.money_manager_backend.model.Transaction;
import com.example.money_manager_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user); // Fetch only user-specific data
}