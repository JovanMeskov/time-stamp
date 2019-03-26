package com.example.demo;

import com.example.demo.domain.Timestamp;
import com.example.demo.service.TimestampService;
import com.example.demo.service.impl.TimestampServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TimestampServiceTest {

    private TimestampService timestampService;

    @Before
    public void setup() {
        timestampService = new TimestampServiceImpl();
    }

    @Test
    public void get_timestamp() {
        String date = "January 1, 2016";
        Timestamp actual = timestampService.getTimestamp(date);
        Timestamp expected = new Timestamp(1451606400L, "January 1, 2016");
        assertEquals(actual, expected);
    }
}
