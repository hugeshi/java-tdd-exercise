package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetVisitor {
    private Map<YearMonth, Long> budgetMap;

    public BudgetVisitor(BudgetRepo repo) {
        List<Budget> budgets = repo.findAll();

        budgetMap = new HashMap<>();
        // TODO not check if there is duplicated month and warn or fail
        for (Budget b : budgets) {
            budgetMap.put(b.getMonth(), b.getAmount());
        }
    }

    /**
     * Get the budget amount of given month.
     * If cannot find a budget record for the month, consider the amount of the month to be zero
     * @param month
     * @return
     */
    public long getAmount(YearMonth month) {
        if (!budgetMap.containsKey(month)) {
            return 0L;
        }
        return budgetMap.get(month);
    }

    public long getMonthAmount(LocalDate d) {
        return getAmount(YearMonth.of(d.getYear(), d.getMonthValue()));
    }
}
