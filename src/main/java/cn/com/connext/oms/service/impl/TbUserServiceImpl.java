package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbAbnormal;
import cn.com.connext.oms.entity.TbUser;
import cn.com.connext.oms.mapper.TbUserMapper;
import cn.com.connext.oms.service.TbUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: oms
 * @description: 对用户的操作
 * @author: Lili.Chen
 * @create: 2019-01-13 18:53
 **/
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;


    /**
    * @Description: 添加用户
    * @Param: [user]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    @Override
    public boolean addUser(TbUser user) {
        Date date=new Date();
        int result=0;//保存更新用户表条数
        TbUser tbUser=tbUserMapper.getUserByName(user.getUserName());//根据用户名查看用户
        //
        //判断角色表是否有相应的角色（根据角色id查看）
        //
        if(tbUser==null){//如果该用户名不存在
            user.setCreated(date);//保存创建时间
           result= tbUserMapper.addUser(user);
           if(result==1){//添加成功
               return true;
           }
        }
        return false;
    }

    /**
    * @Description: 删除用户
    * @Param: [userId]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    @Override
    public boolean deleteUser(Integer userId) {
        int result=0;//保存删除的记录数
        TbUser user=tbUserMapper.getUserById(userId);
        if(user!=null){
         result=tbUserMapper.deleteUser(userId);
         if(result==1){
             return true;
         }
        }
        return false;
    }

    /**
    * @Description: 更改用户信息
    * @Param: [user]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    @Override
    public boolean updateUser(TbUser user) {
        Date date=new Date();
        int result=0;//保存更改记录
        TbUser tbUser=tbUserMapper.getUserById(user.getUserId());
        if(tbUser!=null){//如果存在该用户
            tbUser.setUpdated(date);//保存更改时间
            result=tbUserMapper.updateUser(user);
            if(result==1){
                return true;
            }
        }
        return false;
    }


    /**
    * @Description: 查看所有用户
    * @Param: [currentPage, pageSize]
    * @return: com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbUser>
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    @Override
    public PageInfo<TbUser> getListUser(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<TbUser> userList = tbUserMapper.getAllUserList();

        PageInfo<TbUser> pageInfo=new PageInfo<>(userList);
        return pageInfo;
    }
}
