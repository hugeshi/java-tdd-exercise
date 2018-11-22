package com.odde.tdd;

public class BudgetPlan {
    private final BudgetRepo repo;

    public BudgetPlan(BudgetRepo repo) {

        this.repo = repo;
    }

    public long query(Period period) {
        return repo.findAll().stream()
                .mapToInt(budget -> (int) budget.getOverlappingAmount(period))
                .sum();
    }

}
