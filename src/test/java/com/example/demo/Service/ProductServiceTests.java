package com.example.demo.Service;

import com.example.demo.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {

    @Autowired
    private IProductService productService;

    @Test
    public void findHostList(){
        System.out.println(productService.findHotList());
    }
}
