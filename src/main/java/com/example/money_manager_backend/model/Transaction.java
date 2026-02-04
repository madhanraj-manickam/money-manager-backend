package com.example.money_manager_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

        @Entity
        @Data
        public class Transaction {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private Double amount;
            private String description;
            private String type;
            private String division;
            private String toDivision;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
                this.amount = amount;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDivision() {
                return division;
            }

            public void setDivision(String division) {
                this.division = division;
            }

            public String getToDivision() {
                return toDivision;
            }

            public void setToDivision(String toDivision) {
                this.toDivision = toDivision;
            }

            public LocalDateTime getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
            }

            private String category;
            private LocalDateTime createdAt;

            @PrePersist
            protected void onCreate() {
                this.createdAt = LocalDateTime.now();
            }
        }