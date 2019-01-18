package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.entity.TbUser;
import cn.com.connext.oms.service.TbUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: OMS4Intern
 * @description: 用户操作
 * @author: Lili.Chen
 * @create: 2019-01-16 10:58
 **/
@RestController
public class UserController {
    @Autowired
    private TbUserService tbUserService;



    /**
    * @Description: 增加用户
    * @Param: [refundIdList]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    @PostMapping("/index/addUser")
    @ApiOperation(value = "增加用户")
    public String refund(TbUser user){
        boolean b=tbUserService.addUser(user);
        if(b){
            return "success";
        }
        return "fail";
    }

    @PostMapping("/index/getUserByName")
    @ApiOperation(value = "验证是否有名字相同的用户")
    public String getUserByName(String userName){
        String data=tbUserService.validateName(userName);
        return data;
    }


    @PostMapping("/index/deleteUser")
    @ApiOperation(value = "删除用户")
    public String  deleteUser(Integer userId){
        boolean b=tbUserService.deleteUser(userId);
        if(b){
            return "success";
        }
        return "fail";
    }


    @PostMapping("/index/editUser")
    @ApiOperation(value = "编辑用户")
    public String  updateUser(TbUser user, HttpServletRequest request){
        Integer b=tbUserService.updateUser(user);
        HttpSession session=request.getSession();
        if(b==1){
            return "success";
        }
        else if(b==2){
            session.removeAttribute("OMSUSER");
            return "mySelf";

        }
        return "fail";

    }


    @PostMapping("/index/getUserById")
    @ApiOperation(value = "根据用户id获得用户信息")
    public Map getUserById(Integer userId){
        Map map=new HashMap();//保存返回信息
       TbUser user=tbUserService.getUserById(userId);
       map.put("user",user);
      /* map.put("roleName",user.getTbRole().getRoleName());*/
       return map;
    }


    }
