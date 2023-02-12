package com.bt.assessment.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
@WebAppConfiguration

public class AdminApplicationTests {

    @Test
    public void contextLoads() {
        assertTrue(true);
        // do nothing, just to test that the application context loads
    }

}
