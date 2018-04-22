package com.nba.datacenter;

import com.nba.datacenter.utils.OracleUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:application.properties")
public class DatacenterApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testConnection() {
        try {
            System.out.println(OracleUtil.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
