package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
/** 购物车数据的Value Object类 */
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;
/**
 * get,set
 * equals和hashCode
 * toString
 */
}
