package com.odde.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hujshi on 2018/11/21.
 */
public class FizzBuzzTest {
    @Test
    public void testFizz(){
        FizzBuzzInterface fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.getResult(3);
        assertEquals("Fizz", result);

    }

    @Test
    public void testBuzz(){
        FizzBuzzInterface fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.getResult(5);
        assertEquals("Buzz", result);

    }

    @Test
    public void testCommon(){
        FizzBuzzInterface fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.getResult(4);
        assertEquals("4", result);

    }

    @Test
    public void testFizzBuzz(){
        FizzBuzzInterface fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.getResult(15);
        assertEquals("FizzBuzz", result);

    }

    @Test
    public void testFizzBuzzSequence(){
        FizzBuzzInterface fizzBuzz = new FizzBuzz();
        String result1 = fizzBuzz.getResult(1);
        String result2 = fizzBuzz.getResult(2);
        String result3 = fizzBuzz.getResult(3);
        String result4 = fizzBuzz.getResult(4);
        String result5 = fizzBuzz.getResult(5);
        StringBuffer sb = new StringBuffer();
        String actual = sb.append(result1).append(result2).append(result3).append(result4).append(result5).toString();
        assertEquals("12Fizz4Buzz", actual);

    }

}
