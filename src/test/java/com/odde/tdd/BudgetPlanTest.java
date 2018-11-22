package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetPlanTest {
    BudgetRepo repo = mock(BudgetRepo.class);
    BudgetPlan plan = new BudgetPlan(repo);

    private void givenBudgets(Budget... budgets) {
        when(repo.findAll()).thenReturn(Arrays.asList(budgets));
    }

    private void assertQueryAmount(LocalDate start, LocalDate end, int expected) {
        assertEquals(expected, plan.query(new Period(start, end)));
    }

    @Test
    public void no_budget() {
        givenBudgets();
        assertQueryAmount(LocalDate.of(2018, 11, 5), LocalDate.of(2018, 11, 10), 0);
    }

    @Test
    public void query_whole_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 30));
        assertQueryAmount(
                LocalDate.of(2018, 11, 1),
                LocalDate.of(2018, 11, 30),
                30);
    }

    @Test
    public void query_1_day() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 30));
        assertQueryAmount(
                LocalDate.of(2018, 11, 3),
                LocalDate.of(2018, 11, 3),
                1);
    }

    @Test
    public void query_2_day() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 30));
        assertQueryAmount(
                LocalDate.of(2018, 11, 3),
                LocalDate.of(2018, 11, 4),
                2);
    }

    @Test
    public void query_start_out_of_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 30));
        assertQueryAmount(
                LocalDate.of(2018, 10, 25),
                LocalDate.of(2018, 11, 8),
                8);
    }

    @Test
    public void query_end_out_of_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 30));
        assertQueryAmount(
                LocalDate.of(2018, 11, 25),
                LocalDate.of(2018, 12, 8),
                6);
    }

    @Test
    public void query_out_of_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 30));
        assertQueryAmount(
                LocalDate.of(2018, 10, 25),
                LocalDate.of(2018, 10, 27),
                0);
    }

    @Test
    public void various_daily_amount() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 300));
        assertQueryAmount(
                LocalDate.of(2018, 11, 25),
                LocalDate.of(2018, 11, 27),
                30);
    }

    @Test
    public void query_2_budgets() {
         givenBudgets(
                 new Budget(YearMonth.of(2018, 11), 300),
                 new Budget(YearMonth.of(2018, 12), 3100)
                 );
        assertQueryAmount(
                LocalDate.of(2018, 11, 25),
                LocalDate.of(2018, 12, 27),
                60 + 2700);
    }
}
