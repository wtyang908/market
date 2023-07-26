package com.example.demo.Service;

import com.example.demo.entity.Address;
import com.example.demo.service.IAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("175726");
        address.setName("男朋友");
        addressService.addNewAddress(6,"mxy",address);
    }

    @Test
    public void setDefault() {
        addressService.setDefault(1,6,"管理员");
    }

    @Test
    public void delete() {
        addressService.delete(5,6,"4.11删除");
    }

}
