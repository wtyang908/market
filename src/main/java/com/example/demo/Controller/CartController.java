package com.example.demo.Controller;

import com.example.demo.service.ICartService;
import com.example.demo.util.JsonResult;
import com.example.demo.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.addToCart(
                getuidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data = cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<List<CartVO>>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(
                cid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<Integer>(OK, data);
    }


    @RequestMapping("list")
    public JsonResult<List<CartVO>> findVOByCids(Integer[] cids, HttpSession session) {
        List<CartVO> data = cartService.getVOByCids(getuidFromSession(session), cids);
        return new JsonResult<>(OK, data);
    }




}
