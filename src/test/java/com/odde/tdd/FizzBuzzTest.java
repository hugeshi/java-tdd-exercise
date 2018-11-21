package com.odde.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void testMultiplesOfThree() {
        assertEquals("Fizz", fizzBuzz.report(3));
        assertEquals("Fizz", fizzBuzz.report(6));
    }

    @Test
    public void testMultiplesOfFive() {
        assertEquals("Buzz", fizzBuzz.report(5));
        assertEquals("Buzz", fizzBuzz.report(10));
    }

    @Test
    public void testNormalNumbers() {
        assertEquals("1", fizzBuzz.report(1));
        assertEquals("31", fizzBuzz.report(31));
    }

    @Test
    public void testFizzBuzz(){
        assertEquals("FizzBuzz", fizzBuzz.report(15));
        assertEquals("FizzBuzz", fizzBuzz.report(30));
    }
}
