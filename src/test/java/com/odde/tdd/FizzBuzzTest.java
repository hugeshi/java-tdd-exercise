package com.odde.tdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    FizzBuzz fb = new FizzBuzz();

    @Test
    public void normal() {
        assertEquals("1", fb.fizzBuzz(1));
    }

    @Test
    public void three() {
        assertEquals("Fizz", fb.fizzBuzz(3));
    }

    @Test
    public void five() {
        assertEquals("Buzz", fb.fizzBuzz(5));
    }

    @Test
    public void fifteen() {
        assertEquals("FizzBuzz", fb.fizzBuzz(15));
    }
}
