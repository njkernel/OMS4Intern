package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @program: OMS4Intern
 * @description: 用户操作
 * @author: Lili.Chen
 * @create: 2019-01-16 10:58
 **/
@Controller
public class UserController {
    @Autowired
    private TbUserService tbUserService;


    /** 
    * @Description: 查看用户首页
    * @Param: [page, size, model] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2019/1/16 
    */
    @RequestMapping("/index/getAllUser")
    public String getAllUser(Integer page, Integer size, Model model){
        Map<String,Object> map=tbUserService.getListUser(page,size);
        model.addAttribute("map",map);
        return "pages/details/user/administrator";
    }



    }
