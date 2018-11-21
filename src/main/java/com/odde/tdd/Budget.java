package com.odde.tdd;

import java.time.YearMonth;

public class Budget {
    public YearMonth getMonth() {
        return month;
    }

    public long getAmount() {
        return amount;
    }

    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }
}
