package com.example.demo.entity;

import lombok.Data;

@Data
/** 商品数据的实体类 */
public class Product extends BaseEntity {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;
 /**
 * get,set
 * equals和hashCode
 * toString
 */
}
