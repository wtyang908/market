package com.example.demo.mapper;

import com.example.demo.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {

    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(6);
        address.setPhone("133336");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(6);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> byUid =
                addressMapper.findByUid(6);
        System.out.println(byUid);
    }

    @Test
    public void findByAid() {
        System.err.println(addressMapper.findByAid(3));
    }

    @Test
    public void updateNonDefault() {
        System.out.println(addressMapper.updateNonDefault(6));//有几条数据影响行数就输出几
    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(3,"明明",new Date());
    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(1);
    }

    @Test
    public void findLastModified() {
        System.out.println(addressMapper.findLastModified(6));
    }
}


