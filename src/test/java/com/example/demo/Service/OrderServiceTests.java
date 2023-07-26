package com.example.demo.Service;

import com.example.demo.entity.Order;
import com.example.demo.service.IOrderService;
import com.example.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Autowired
    IUserService userService;

    @Test
    public void create() {
        Integer[] cids = {2,4};
        Order order = orderService.create(4, cids, 6, "小红");
        System.out.println(order);
    }
}
