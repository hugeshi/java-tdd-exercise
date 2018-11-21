package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetFinderTest {
    BudgetRepo repo = mock(BudgetRepo.class);
    BudgetFinder finder = new BudgetFinder(repo);

    private void givenBudgets(Budget... budgets) {
        when(repo.findAll()).thenReturn(Arrays.asList(
                budgets
        ));
    }

    @Test
    public void across_multiple_budgets() {
        givenBudgets(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        );
        assertQueryAmount(LocalDate.of(2018, 7, 15), LocalDate.of(2018, 10, 10), 17 + 310 + 3000 + 100);
    }

    private void assertQueryAmount(LocalDate start, LocalDate end, double expected) {
        assertEquals(expected, finder.findBudget(start, end), 0.001);
    }

    @Test
    public void full_month() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertQueryAmount(LocalDate.of(2018, 7, 1), LocalDate.of(2018, 7, 31), 31);
    }

    @Test
    public void within_one_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertQueryAmount(LocalDate.of(2018, 7, 3), LocalDate.of(2018, 7, 5), 3);
    }

    @Test
    public void start_outside_range() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertQueryAmount(LocalDate.of(2018, 6, 15), LocalDate.of(2018, 7, 5), 5);
    }

    @Test
    public void end_outside_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertQueryAmount(LocalDate.of(2018, 7, 15), LocalDate.of(2018, 8, 5), 17);
    }

    @Test
    public void full_outside_range() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertQueryAmount(LocalDate.of(2018, 4, 1), LocalDate.of(2018, 5, 31), 0);
    }

    @Test
    public void float_amount() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 51));
        assertQueryAmount(LocalDate.of(2018, 10, 10), LocalDate.of(2018, 11, 2), 2.0 * 51 / 30);
    }

    @Test
    public void empty_repo() {
        givenBudgets();
        assertQueryAmount(LocalDate.of(2018, 10, 10), LocalDate.of(2018, 11, 2), 0);
    }
}
