package com.iorta.sboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iorta.sboot.dto.ExpenseDTO;
import com.iorta.sboot.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping("/addExpense")
	public String addExpense(@RequestBody ExpenseDTO expenseDTO) {
		return expenseService.addExpense(expenseDTO);
	}
	
	@GetMapping("/getAllExpenses")
	public List<ExpenseDTO> getAllExpenses() {
		return expenseService.getAllExpenses();
	}
	
	@GetMapping("/getExpenseById/{id}")
	public ExpenseDTO getExpenseById(@PathVariable Long id) { 
		return expenseService.getExpenseById(id);
	}
	
	@GetMapping("/getExpenseByCategory/{category}")
	public List<ExpenseDTO> getExpenseByCategory(@PathVariable String category) {
		return expenseService.getExpenseByCategory(category);
	}
	
	@GetMapping("/getExpenseByDate/{date}")
	public List<ExpenseDTO> getExpenseByDate(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return expenseService.getExpenseByDate(date);
	}
	
	@GetMapping("/getExpenseByAmountRange")
	public List<ExpenseDTO> getExpenseByAmountRange(@RequestParam double min, @RequestParam double max) {
		return expenseService.getExpenseByAmountRange(min, max);
	}
	
	@PutMapping("/updateExpense/{id}")
	public String updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
		return expenseService.updateExpense(id, expenseDTO);
	}
	
	@DeleteMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable Long id) {
		return expenseService.deleteExpense(id);
	}

}
