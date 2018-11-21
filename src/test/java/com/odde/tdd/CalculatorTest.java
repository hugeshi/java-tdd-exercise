package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorTest {
    @Test
    public void one_add_one() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 1);
        assertEquals(2, result);
    }

    private BudgetRepo repo = mock(BudgetRepo.class);
    private Calculator calculator = new Calculator(repo);

    private void givenBudgets(Budget... budgets) {
        when(repo.findAll()).thenReturn(Arrays.asList(
                budgets
        ));
    }

    private void assertQueryAmount(LocalDate start, LocalDate end, double expected) {
        assertEquals(expected, calculator.calcBudget(start, end), 0.001);
    }

    private void assertCalcAmount(String startStr, String endStr, double expected) {
        LocalDate start = LocalDate.parse(startStr);
        LocalDate end = LocalDate.parse(endStr);
        assertEquals(expected, calculator.calcBudget(start, end), 0.001);
    }

    @Test
    public void across_multiple_budgets() {
        givenBudgets(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        );
        assertCalcAmount("2018-07-15", "2018-10-10", 17 + 310 + 3000 + 100);
    }

    @Test
    public void full_month() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertCalcAmount("2018-07-01", "2018-07-31", 31);
    }

    @Test
    public void within_one_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertCalcAmount("2018-07-03", "2018-07-05", 3);
    }

    @Test
    public void start_outside_range() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertCalcAmount("2018-06-15", "2018-07-05", 5);
    }

    @Test
    public void end_outside_budget() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertCalcAmount("2018-07-15", "2018-08-05", 17);
    }

    @Test
    public void full_outside_range() {
        givenBudgets(new Budget(YearMonth.of(2018, 7), 31));
        assertCalcAmount("2018-04-01", "2018-05-31", 0);
    }

    @Test
    public void float_amount() {
        givenBudgets(new Budget(YearMonth.of(2018, 11), 51));
        assertCalcAmount("2018-10-10", "2018-11-02", 2.0 * 51 / 30);
    }

    @Test
    public void empty_repo() {
        givenBudgets();
        assertCalcAmount("2018-10-10", "2018-11-02", 0);
    }
}
