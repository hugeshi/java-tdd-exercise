package com.odde.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void testMultiplesOfThree() {
        assertEquals("Fizz", FizzBuzz.report(3));
        assertEquals("Fizz", FizzBuzz.report(6));
    }

    @Test
    public void testMultiplesOfFive() {
        assertEquals("Buzz", FizzBuzz.report(5));
        assertEquals("Buzz", FizzBuzz.report(10));
    }

    @Test
    public void testNormalNumbers() {
        assertEquals("1", FizzBuzz.report(1));
        assertEquals("31", FizzBuzz.report(31));
    }

    @Test
    public void testFizzBuzz(){
        assertEquals("FizzBuzz", FizzBuzz.report(15));
        assertEquals("FizzBuzz", FizzBuzz.report(30));
    }
}
