package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数(增删改都将受影响的行数作为返回值,可以根据返回值来判断是否执行成功)
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据,如果没有找到则返回null
     */
    User findByUsername(String username);

    /**
     * 参数为user的方法
     * @param uid 用户的id
     * @param password 用户的密码
     * @param modifiedUser 用户的信息修改者
     * @param modifiedTime 用户的信息修改时间
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);//也可以用三个String的形参接收电话,邮箱,性别,但不如直接写个user省事

    /**
     * 根据用户id来查询用户的数据
     * @param uid 用户id
     * @return 如果找到对应的用户则返回这个用户的数据,如果没有找到则返回null
     */
    User findByUid(Integer uid);


    /**
     * 参数为user的方法
     * @param user 用户的数据
     * @return 返回值为受影响的行数
     */
    Integer updateInfoByUid(User user);//也可以用三个String的形参接收电话,邮箱,性别,但不如直接写个user省事


    /**
     * 根据用户uid修改用户的头像
     * @param iddddd
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    /**
     * 注解@Param("SQL映射文件中#{}占位符的变量名"),解决的问题:
     * 当SQL语句的占位符和映射的接口方法参数名不一致时,需要将某个参数强行注入到某个
     * 占位符变量上时,可以使用@Param这个注解来标注映射的关系
     * */
    Integer updateAvatarByUid(@Param("uid") Integer uid,//@Param("参数名")注解中的参数名需要和sql语句中
                              //的#{参数名}的参数名保持一致.
                              String avatar,
                              String modifiedUser,
                              Date modifiedTime);

}
