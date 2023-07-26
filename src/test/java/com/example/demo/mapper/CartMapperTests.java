package com.example.demo.mapper;

import com.example.demo.entity.Cart;
import com.example.demo.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
    @Autowired(required = false)
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(6);
        cart.setPid(10000001);
        cart.setNum(3);
        cart.setPrice(4L);//长整型
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "张三", new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(6, 10000001);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVOByUid(6);
        System.out.println(list);
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 4};//可以写表中不存在的,无非就是查不到数据,并不会报错
        List<CartVO> list = cartMapper.findVOByCids(cids);
        for (CartVO item : list) {
            System.out.println(item);
        }
    }


}
