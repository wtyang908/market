package com.example.demo.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {

    @Autowired(required = false)
    private ProductMapper productMapper;
}
