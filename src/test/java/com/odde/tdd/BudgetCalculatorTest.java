package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BudgetCalculatorTest {
    BudgetRepo repo = new FakeBudgetRepo();
    BudgetVisitor visitor = new BudgetVisitor(repo);
    BudgetCalculator calc = new BudgetCalculator(visitor);

    @Test
    public void testSameMonthFull() {
        long actual = calc.calcBudget(
                LocalDate.of(2018, 7, 1),
                LocalDate.of(2018, 7, 31));
        assertEquals(31, actual);
    }

    @Test
    public void testSameMonthPartial() {
        long actual = calc.calcBudget(
                LocalDate.of(2018, 7, 3),
                LocalDate.of(2018, 7, 5));
        assertEquals(3, actual);
    }

    @Test
    public void testTwoMonthsFull() {
        long actual = calc.calcBudget(
                LocalDate.of(2018, 7, 1),
                LocalDate.of(2018, 8, 31));
        assertEquals(341, actual);
    }

    @Test
    public void testTwoMonthsPartial() {
        long actual = calc.calcBudget(
                LocalDate.of(2018, 7, 10),
                LocalDate.of(2018, 8, 15));
        assertEquals(172, actual);
    }

    @Test
    public void testThreeMonthsFull() {
        long actual = calc.calcBudget(
                LocalDate.of(2018, 7, 1),
                LocalDate.of(2018, 9, 30));
        assertEquals(3341, actual);
    }

    @Test
    public void testThreeMonthsPartial() {
        long actual = calc.calcBudget(
                LocalDate.of(2018, 7, 10),
                LocalDate.of(2018, 9, 20));
        assertEquals(2332, actual);
    }

    @Test
    public void testCrossYearPartial() {
        long actual = calc.calcBudget(
                LocalDate.of(2017, 7, 10),
                LocalDate.of(2018, 8, 10));
        assertEquals(153, actual);
    }

    public class FakeBudgetRepo implements BudgetRepo {
        private Budget[] budgets = new Budget[]{
                // 2017
                new Budget(YearMonth.of(2017, 7), 31),
                // 2018
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000)
        };

        @Override
        public List<Budget> findAll() {
            return Arrays.asList(budgets);
        }
    }
}
