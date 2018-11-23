package com.odde.tdd;

class Calculator {

    private BudgetRepo budgetRepo;

    Calculator(BudgetRepo budgetRepo){
        this.budgetRepo = budgetRepo;
    }
    Long getBudget(Period period) {
        return budgetRepo.findAll().stream().mapToLong(budget -> budget.getAmount(period)).sum();
    }

}
