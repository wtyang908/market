package com.example.demo.Controller;

import com.example.demo.Controller.ex.*;
import com.example.demo.service.ex.*;
import com.example.demo.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    //操作成功
    public static final int OK=200;

    /**
     * 1.@ExceptionHandler表示该方法用于处理捕获抛出的异常
     * 2.什么样的异常才会被这个方法处理呢?所以需要ServiceException.class,这样的话只要是抛出ServiceException异常就会被拦截到handleException方法,此时handleException方法就是请求处理方法,返回值就是需要传递给前端的数据
     * 3.被ExceptionHandler修饰后如果项目发生异常,那么异常对象就会被自动传递给此方法的参数列表上,所以形参就需要写Throwable e用来接收异常对象
     */
    @ExceptionHandler({ServiceException.class,FileUploadException.class,})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入数据时产生未知的异常");
        }else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在异常");
        }else if (e instanceof PasswordNotException) {
            result.setState(5002);
            result.setMessage("密码错误异常");
        }else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("插入数据时产生异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限的异常");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("用户的收货地址数据不存在的异常");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("收货地址数据非法访问的异常");
        }else if (e instanceof DeleteException) {
            result.setState(5004);
            result.setMessage("删除数据时产生未知的异常");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("访问的商品数据不存在的异常");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("购物车表不存在该商品的异常");
        }






        return result;
    }


    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    protected  final  Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session session对象
     * @return 当前登录的用户username的值
     */
    protected  final  String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }


}
