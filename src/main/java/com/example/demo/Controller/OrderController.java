package com.example.demo.Controller;

import com.example.demo.entity.Order;
import com.example.demo.service.IOrderService;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Order data = orderService.create(
                aid,
                cids,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }
}
