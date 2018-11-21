package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class Calculator {

    private BudgetRepo budgetRepo;

    public Calculator(BudgetRepo budgetRepo){
        this.budgetRepo = budgetRepo;
    }

    public long getBudgetByMonth(LocalDate startDate){
        YearMonth startMonth = YearMonth.of(startDate.getYear(),startDate.getMonth());
        int dayOfMonth = startDate.getDayOfMonth();
        int lengthOfMonth = startMonth.lengthOfMonth();
        int dayRange = lengthOfMonth - dayOfMonth + 1;
        for(Budget budget:budgetRepo.findAll()){
            YearMonth yearMonthBudget = budget.getMonth();
            if(yearMonthBudget.compareTo(startMonth)==0){
                long budgetAmount = budget.getAmount();
                return budgetAmount/lengthOfMonth * dayRange;
            }
        }
        return 0;
    }


    private long getEndMonthBudget(LocalDate endDate){
        YearMonth endMonth = YearMonth.of(endDate.getYear(),endDate.getMonth());
        int dayOfMonth = endDate.getDayOfMonth();
        int lengthOfMonth = endMonth.lengthOfMonth();
        int dayRange = dayOfMonth;
        for(Budget budget:budgetRepo.findAll()){
            YearMonth yearMonthBudget = budget.getMonth();
            if(yearMonthBudget.compareTo(endMonth)==0){
                long budgetAmount = budget.getAmount();
                return budgetAmount/lengthOfMonth * dayRange;
            }
        }
        return 0;
    }

    private long getBudgetByDayRange(YearMonth yearMonth,int dayRange){
        int lengthOfMonth = yearMonth.lengthOfMonth();
        for(Budget budget:budgetRepo.findAll()){
            YearMonth yearMonthBudget = budget.getMonth();
            if(yearMonthBudget.compareTo(yearMonth)==0){
                long budgetAmount = budget.getAmount();
                return budgetAmount/lengthOfMonth * dayRange;
            }
        }
        return 0;
    }


    private long getBudgetByMonth(YearMonth yearMonth){
        for(Budget budget:budgetRepo.findAll()){
            YearMonth yearMonthBudget = budget.getMonth();
            if(yearMonthBudget.compareTo(yearMonth)==0){
                long budgetAmount = budget.getAmount();
                return budgetAmount;
            }
        }
        return 0;
    }


    public long getBudget(LocalDate startDate,LocalDate endDate){
        YearMonth startMonth = YearMonth.of(startDate.getYear(),startDate.getMonth());
        YearMonth endMonth = YearMonth.of(endDate.getYear(),endDate.getMonth());
        if(startMonth.compareTo(endMonth)==0){
            int dayRange = endDate.getDayOfMonth() - startDate.getDayOfMonth() +1;
            return getBudgetByDayRange(startMonth,dayRange);
        }else if(startMonth.isBefore(endMonth)){
            long amount = 0;
            YearMonth nextMonth = startMonth.plus(1, ChronoUnit.MONTHS);
            amount = amount + getBudgetByMonth(startDate);
            while(nextMonth.isBefore(endMonth)){
                amount = amount + getBudgetByMonth(nextMonth);
                nextMonth = nextMonth.plus(1, ChronoUnit.MONTHS);
            }
            amount = amount + getEndMonthBudget(endDate);
            return amount;
        }
        return 0;
    }

}
