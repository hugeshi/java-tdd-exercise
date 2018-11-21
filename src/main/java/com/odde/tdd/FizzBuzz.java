package com.odde.tdd;

/**
 * Created by hujshi on 2018/11/21.
 */
public class FizzBuzz implements FizzBuzzInterface{
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    @Override
    public String getResult(int number) {
        StringBuilder result = new StringBuilder();
        if(number%3 == 0 && number%5 != 0){
            result.append(FIZZ);
        }else if(number%5 == 0 && number%3 != 0){
            result.append(BUZZ);
        }else if(number%5 == 0 && number%3 == 0){
            result.append(FIZZ).append(BUZZ);
        }else{
            result.append(Integer.toString(number));
        }
        return result.toString();
    }
}
