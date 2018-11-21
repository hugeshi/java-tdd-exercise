package com.odde.tdd;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetFinderTest {
    BudgetRepo repo = mock(BudgetRepo.class);
    BudgetFinder finder = new BudgetFinder(repo);

    @Test
    public void normal() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(17 + 310 + 3000 + 100, finder.findBudget(LocalDate.of(2018, 7, 15), LocalDate.of(2018, 10, 10)), 0.001);
    }

    @Test
    public void beginning_of_the_month() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(31 + 310 + 2200, finder.findBudget(LocalDate.of(2018, 7, 1), LocalDate.of(2018, 9, 22)), 0.001);
    }

    @Test
    public void end_of_the_month() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(220 + 3000 + 310, finder.findBudget(LocalDate.of(2018, 8, 10), LocalDate.of(2018, 10, 31)), 0.001);
    }

    @Test
    public void full_month() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(31 + 310 + 3000 + 310, finder.findBudget(LocalDate.of(2018, 7, 1), LocalDate.of(2018, 10, 31)), 0.001);
    }

    @Test
    public void partial_month() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(3, finder.findBudget(LocalDate.of(2018, 7, 3), LocalDate.of(2018, 7, 5)), 0.001);
    }

    @Test
    public void partial_outside_range() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(31 + 310 + 3000 + 310, finder.findBudget(LocalDate.of(2018, 4, 1), LocalDate.of(2018, 10, 31)), 0.001);
    }

    @Test
    public void full_outside_range() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310)
        ));
        assertEquals(0, finder.findBudget(LocalDate.of(2018, 4, 1), LocalDate.of(2018, 5, 31)), 0.001);
    }

    @Test
    public void float_amount() {
        when(repo.findAll()).thenReturn(Arrays.asList(
                new Budget(YearMonth.of(2018, 7), 31),
                new Budget(YearMonth.of(2018, 8), 310),
                new Budget(YearMonth.of(2018, 9), 3000),
                new Budget(YearMonth.of(2018, 10), 310),
                new Budget(YearMonth.of(2018, 11), 51)
        ));
        assertEquals(220 + 2.0 * 51 / 30, finder.findBudget(LocalDate.of(2018, 10, 10), LocalDate.of(2018, 11, 2)), 0.001);
    }

    @Test
    public void empty_repo() {
        when(repo.findAll()).thenReturn(new ArrayList<Budget>());
        assertEquals(0, finder.findBudget(LocalDate.of(2018, 10, 10), LocalDate.of(2018, 11, 2)), 0.001);
    }
}
