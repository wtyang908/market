package com.example.demo.service.impl;

import com.example.demo.entity.Address;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.service.IAddressService;
import com.example.demo.service.IDistrictService;
import com.example.demo.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired(required = false)
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;


    @Override
    public void addNewAddress(Integer uid, String username, Address address) {

        Integer count = addressMapper.countByUid(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }


        /**
         * 对address对象中的数据进行补全:省市区的名字看前端代码发现前端传递过来的省市区的name分别为:
         * provinceCode,cityCode,areaCode,所以这里可以用address对象的get方法获取这三个的数据
         */
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //uid,isDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;//1表示默认收货地址,0反之
        address.setIsDefault(isDefault);

        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //调用插入收货地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入用户的收货地址时产生未知异常");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        /**
         * 收货地址列表在前端只展示了四个数据,这里让其他值为空降低数据量
         * ProvinceName,CityName,AreaName,aid,tel(确认订单页展示展示用户地
         * 址时用到tel)在展示地址列表用不到,但是后面提交订单时的地址会用到,所以这里不设为null
         * */
        for (Address address : list) {
            //address.setAid(null);
            address.setUid(null);
            //address.setProvinceName(null);
            address.setProvinceCode(null);
            //address.setCityName(null);
            address.setCityCode(null);
            //address.setAreaName(null);
            address.setAreaCode(null);
            address.setZip(null);
            //address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw  new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据时产生异常");
        }
         rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(rows != 1 ){
            throw new UpdateException("更新数据时产生异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除数据产生未知异常");
        }
        if(result.getIsDefault()!=1){
            return;
        }


        Integer count = addressMapper.countByUid(uid);
        if(count==0){
            return;
        }
        Address address = addressMapper.findLastModified(uid);
        rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生异常");
        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {

        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("收货地址数据不存在的异常");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }

}
