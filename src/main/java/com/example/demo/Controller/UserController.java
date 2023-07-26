package com.example.demo.Controller;

import com.example.demo.Controller.ex.*;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.ex.InsertException;
import com.example.demo.service.ex.UsernameDuplicatedException;
import com.example.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /*  @RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result = new JsonResult<>();
        try{
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        }catch (UsernameDuplicatedException e){
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e){
            result.setState(5000);
            result.setMessage("注册时产生异常");
        }
        return result;
    }*/

    /*springboot约定大于配置
      请求处理方法的参数列表设置为非pojo类型:

        SpringBoot会直接将请求的参数名和方法的参数名直接进行比较,如果名称相同则自动完成值的依赖注入

      请求处理方法的参数列表设置为pojo类型:

        SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较,如果这两个名称相同,则将值注入到pojo类中对应的属性上

    * */
//    服务器本身自动创建有session对象,已经是一个全局的session对象,所以我们需要想办法获取session对象:如果直接将HttpSession类型的对象作为请求处理方法的参数,
//    这时springboot会自动将全局的session对象注入到请求处理方法的session形参上:
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user= userService.login(username,password);
        //session
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());

        System.out.println(getUsernameFromSession(session));
        System.out.println(getuidFromSession(session));
        return new JsonResult<>(OK,user);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data=userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }


    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        userService.changeInfo(getuidFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }

    //限制上传的数据大小
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;

    //限制上传文件类型
    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     *
     * @param session
     * @param file  springboot自动注入
     * @RequestParam处理变量名不一样的问题
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){

        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        String parent = session.getServletContext().getRealPath("upload");
        File dir=new File(parent);
        if(!dir.exists()){//目录不存在
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename="+originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        //filename形如SAFS1-56JHIOHI-HIUGHUI-5565TYRF.png
        String filename =
                UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest =new File(dir,filename);//空文件
        try {
            file.transferTo(dest);//transferTo是一个封装的方法,用来将file文件中的数据写入到dest文件

            /**
             * 先捕获FileStateException再捕获IOException是
             * 因为后者包含前者,如果先捕获IOException那么
             * FileStateException就永远不可能会被捕获
             */
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            //这里不用打印e,而是用自己写的FileUploadIOException类并
            // 抛出文件读写异常
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid=getuidFromSession(session);
        String avatar ="/upload/"+filename;
        userService.changeAvatar(uid,avatar,getUsernameFromSession(session));

        return new JsonResult<>(OK,avatar);
    }
}
