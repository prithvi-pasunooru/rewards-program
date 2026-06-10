
package com.rewards.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long customerId;
	private double amount;
	private LocalDate transactionDate;

	public Transaction() {
	}
	public Transaction(Long customerId, double amount, LocalDate transactionDate) {
	this.customerId=customerId;
	this.amount=amount;
	this.transactionDate=transactionDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}
}
