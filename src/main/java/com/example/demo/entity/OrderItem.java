package com.example.demo.entity;

import lombok.Data;

/** 订单中的商品数据 */
@Data
public class OrderItem extends BaseEntity {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
    /**
     * get,set
     * equals和hashCode
     * toString
     */
}    
