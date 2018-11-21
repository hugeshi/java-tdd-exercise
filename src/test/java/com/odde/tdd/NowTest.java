package com.odde.tdd;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class NowTest {
    @Test
    public void testNow(){
        String actual = new Now().getString().substring(0, 20);
        String expected = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()).substring(0, 20);
        assertEquals(expected, actual);
    }
}
