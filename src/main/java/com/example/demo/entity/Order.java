package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

/** 订单数据的实体类 */
@Data
public class Order extends BaseEntity {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
    /**
     * get,set
     * equals和hashCode
     * toString
     */
}    
