package com.example.demo.mapper;


import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest

/**
 * 1.@RunWith表示启动这个单元测试类,否则这个单元测试类是不能运行的,需要传递
 * 一个参数,该参数必须是SpringRunner.class即SpringRunner的实例类型
 * 2.敲完@RunWith(SpringRunner.class)后鼠标分别放在SpringRunner和@RunWith上按alt+enter分别导入包
 * 3.单元测试类中出现的方法必须是单元测试方法
 * 4.单元测试方法的特点:必须被@Test注解修饰;返回值类型必须是void;方法的参数列表不指定任何类型;方法的访问修饰符必须是public
 */

@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired(required = false)
    UserMapper userMapper;

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(
                2,
                "321",
                "管理员",
                new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(2));
    }


    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(6);
        user.setPhone("13333688");
        user.setEmail("1454@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid() {
        userMapper.updateAvatarByUid(
                6,
                "abc",
                "mxy",
                new Date());
    }

}
