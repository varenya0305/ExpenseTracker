package com.iorta.sboot.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iorta.sboot.dao.Expense;
import com.iorta.sboot.dto.ExpenseDTO;
import com.iorta.sboot.repository.ExpenseRepository;
import com.iorta.sboot.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public String addExpense(ExpenseDTO expenseDTO) {
		Expense expense = mapper.map(expenseDTO, Expense.class);
		expenseRepository.save(expense);
		return "Expense added successfully!";
	}

	@Override
	public List<ExpenseDTO> getAllExpenses() {
		return expenseRepository.findAll()
				.stream()
				.map(e -> mapper.map(e, ExpenseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ExpenseDTO getExpenseById(Long id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		return expense.map(e -> mapper.map(e, ExpenseDTO.class)).orElse(null);
	}

	@Override
	public String updateExpense(Long id, ExpenseDTO expenseDTO) {
		Optional<Expense> existingExpense = expenseRepository.findById(id);
		if (existingExpense.isPresent()) {
			Expense expense = existingExpense.get();
			expense.setTitle(expenseDTO.getTitle());
			expense.setDescription(expenseDTO.getDescription());
			expense.setAmount(expenseDTO.getAmount());
			expense.setCategory(expenseDTO.getCategory());
			expense.setDate(expenseDTO.getDate());
			expenseRepository.save(expense);
			return "Expense updated successfully!";
		}
		return "Expense not found!";
	}

	@Override
	public String deleteExpense(Long id) {
		expenseRepository.deleteById(id);
		return "Expense deleted successfully!";
	}

	@Override
	public List<ExpenseDTO> getExpenseByDate(LocalDate date) {
		List<Expense> expenses = expenseRepository.findByDate(date);
		return expenses.stream()
				.map(e -> mapper.map(e, ExpenseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ExpenseDTO> getExpenseByAmountRange(double min, double max) {
		List<Expense> expenses = expenseRepository.findByAmountBetween(min, max);
		return expenses.stream()
				.map(e -> mapper.map(e, ExpenseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ExpenseDTO> getExpenseByCategory(String category) {
		List<Expense> expenses = expenseRepository.findByCategory(category);
		return expenses.stream()
				.map(e -> mapper.map(e, ExpenseDTO.class))
				.collect(Collectors.toList());
	}

}
