package com.iorta.sboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iorta.sboot.dao.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	List<Expense> findByCategory(String category);
	
	List<Expense> findByDate(LocalDate date);
	
	List<Expense> findByAmountBetween(double min, double max);

}
