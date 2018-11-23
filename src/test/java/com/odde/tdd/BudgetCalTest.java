package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hujshi on 2018/11/21.
 */
public class BudgetCalTest {
    @Test
    public void getBudgetCrossYearTest(){
        List<Budget> budgetList = new ArrayList<>();
        budgetList.add(new Budget(YearMonth.of(2017,7),31));
        budgetList.add(new Budget(YearMonth.of(2017,8),310));
        budgetList.add(new Budget(YearMonth.of(2017,9),3000));
        budgetList.add(new Budget(YearMonth.of(2017,10),3100));
        budgetList.add(new Budget(YearMonth.of(2017,11),30));
        budgetList.add(new Budget(YearMonth.of(2017,12),31));
        budgetList.add(new Budget(YearMonth.of(2018,1),31));
        budgetList.add(new Budget(YearMonth.of(2018,2),28));

        BudgetRepo budgetRepo = mock(BudgetRepo.class);

        when(budgetRepo.findAll()).thenReturn(budgetList);

        Calculator calculator = new Calculator(budgetRepo);
        LocalDate startDate = LocalDate.of(2017,7,15);
        LocalDate endDate = LocalDate.of(2018,2,2);
        long budget = calculator.getBudget(new Period(startDate, endDate));
        assertEquals(17+310+3000+3100+30+31+31+2,budget);
    }

    @Test
    public void getBudgetTest(){
        List<Budget> budgetList = new ArrayList<>();
        budgetList.add(new Budget(YearMonth.of(2017,7),31));
        budgetList.add(new Budget(YearMonth.of(2017,8),310));
        budgetList.add(new Budget(YearMonth.of(2017,9),3000));
        budgetList.add(new Budget(YearMonth.of(2017,10),3100));
        BudgetRepo budgetRepo = mock(BudgetRepo.class);

        when(budgetRepo.findAll()).thenReturn(budgetList);

        Calculator calculator = new Calculator(budgetRepo);
        LocalDate startDate = LocalDate.of(2017,7,15);
        LocalDate endDate = LocalDate.of(2017,10,17);
        long budget = calculator.getBudget(new Period(startDate, endDate));
        assertEquals(5027,budget);
    }


    @Test
    public void getBudgetInMonthTest(){
        List<Budget> budgetList = new ArrayList<>();
        budgetList.add(new Budget(YearMonth.of(2017,7),31));
        BudgetRepo budgetRepo = mock(BudgetRepo.class);

        when(budgetRepo.findAll()).thenReturn(budgetList);

        Calculator calculator = new Calculator(budgetRepo);
        LocalDate startDate = LocalDate.of(2017,7,15);
        LocalDate endDate = LocalDate.of(2017,7,30);
        long budget = calculator.getBudget(new Period(startDate, endDate));
        assertEquals(16,budget);
    }

    @Test
    public void getBudgetEmptyTest(){
        List<Budget> budgetList = new ArrayList<>();
        BudgetRepo budgetRepo = mock(BudgetRepo.class);

        when(budgetRepo.findAll()).thenReturn(budgetList);

        Calculator calculator = new Calculator(budgetRepo);
        LocalDate startDate = LocalDate.of(2017,7,15);
        LocalDate endDate = LocalDate.of(2017,7,30);
        long budget = calculator.getBudget(new Period(startDate, endDate));
        assertEquals(0,budget);
    }


}
