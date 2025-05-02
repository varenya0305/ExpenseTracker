package com.iorta.sboot.service;

import java.time.LocalDate;
import java.util.List;

import com.iorta.sboot.dto.ExpenseDTO;

public interface ExpenseService {
	
	String addExpense(ExpenseDTO expenseDTO);
	
	List<ExpenseDTO> getAllExpenses();
	
	ExpenseDTO getExpenseById(Long id);
	
	String updateExpense(Long id, ExpenseDTO expenseDTO);
	
	String deleteExpense(Long id);
	
	List<ExpenseDTO> getExpenseByCategory(String category);
	
	List<ExpenseDTO> getExpenseByDate(LocalDate date);
	
	List<ExpenseDTO> getExpenseByAmountRange(double min, double max);

}
