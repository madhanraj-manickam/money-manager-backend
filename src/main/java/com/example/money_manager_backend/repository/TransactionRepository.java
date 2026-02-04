package com.example.money_manager_backend.repository;

import com.example.money_manager_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
