package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.ws.soap.Addressing;
import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
