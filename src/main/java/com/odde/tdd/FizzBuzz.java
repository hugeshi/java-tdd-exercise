package com.odde.tdd;

/**
 * Created by yuche on 2018/11/21.
 */
public class FizzBuzz {
    public String fizzBuzz(Integer i) {
        if (i % 15 == 0) {
            return "FizzBuzz";
        }
        else if (i % 3 == 0) {
            return "Fizz";
        }
        else if (i % 5 == 0) {
            return "Buzz";
        }
        else {
            return i.toString();
        }
    }
}
