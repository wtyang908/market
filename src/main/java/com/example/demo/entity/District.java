package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class District extends BaseEntity {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
