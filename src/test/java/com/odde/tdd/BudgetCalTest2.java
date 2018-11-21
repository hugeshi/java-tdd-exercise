package com.odde.tdd;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by hujshi on 2018/11/21.
 */
public class BudgetCalTest2 {
    @Test
    public void getBudgetByMonthTest(){
        BudgetRepo budgetRepo = new MockImpl();
        Calculator calculator = new Calculator(budgetRepo);
        LocalDate localDate = LocalDate.of(2017,7,15);
        long budget = calculator.getBudgetByMonth(localDate);
        assertEquals(17,budget);
    }



    class MockImpl implements BudgetRepo{
        @Override
        public List<Budget> findAll() {
            List<Budget> budgetList = new ArrayList<>();
            budgetList.add(new Budget(YearMonth.of(2017,7),31));
            budgetList.add(new Budget(YearMonth.of(2017,8),310));
            budgetList.add(new Budget(YearMonth.of(2017,9),3000));
            budgetList.add(new Budget(YearMonth.of(2017,10),3100));
            return budgetList;
        }
    }
}
