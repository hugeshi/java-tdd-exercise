package com.odde.tdd;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

public class NowTest {
    @Test
    public void testNow(){
        String before = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date());
        String now = new Now().getString();
        String after = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date());
        assertTrue(before.compareTo(now) <= 0 && after.compareTo(now) >= 0);
    }
}
