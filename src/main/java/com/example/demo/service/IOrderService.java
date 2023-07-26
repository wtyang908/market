package com.example.demo.service;

import com.example.demo.entity.Order;

public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
