package com.example.demo.service.impl;

import com.example.demo.entity.District;
import com.example.demo.mapper.DistrictMapper;
import com.example.demo.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired(required = false)
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
