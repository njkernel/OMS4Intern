package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbAbnormal;
import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.entity.TbRole;
import cn.com.connext.oms.entity.TbUser;
import cn.com.connext.oms.mapper.TbRoleMapper;
import cn.com.connext.oms.mapper.TbUserMapper;
import cn.com.connext.oms.service.TbUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: oms
 * @description: 用户的业务逻辑层
 * @author: Lili.Chen
 * @create: 2019-01-13 18:53
 **/
@Service
public class TbUserServiceImpl implements TbUserService {
   @Autowired
    private TbUserMapper tbUserMapper;


    @Autowired
    private TbRoleMapper tbRoleMapper;


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
        TbRole role=tbRoleMapper.getRoleById(user.getRoleId());//判断角色表是否有相应的角色（根据角色id查看）
        if(tbUser==null&&role!=null){//如果该用户名不存在,且数据库存在该角色
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
        if(user!=null){//数据库存在该用户
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
        if(user!=null){//传过来的用户不为空
            TbUser tbUser=tbUserMapper.getUserById(user.getUserId());
            if(tbUser!=null){//如果存在该用户
                TbRole role=tbRoleMapper.getRoleById(user.getRoleId());
                if(role!=null&&user.getUserPassword()!=""){
                    tbUser.setUpdated(date);//保存更改时间
                    result=tbUserMapper.updateUser(user);
                    if(result==1){
                        return true;
                    }
                }

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
    public Map getListUser(Integer page, Integer size) {
        Map map=new HashMap<>();//存查看用户分页的参数
        Map map2=new HashMap<>();//存返回值
        Integer beginIndex=0;//查看数据库用户列表列表开始的索引
        List<TbUser> userList=new ArrayList<>();//存分页中返回的退款单
        Integer prePage=0;//保存是否有前一页
        Integer nextPage=0;//保存是否有后一页
        Integer pageCount=1;
        List<TbUser> users=tbUserMapper.getAllUserList();
        if(!users.isEmpty()){
            Integer count=users.size();//总共的用户信息记录条数
            pageCount=count/size;//总共的页数
            if(count%size!=0){
                pageCount++;
            }
            if(page<1){
                page=1;
            }
            if(page>pageCount){
                page=pageCount;
            }
            beginIndex=(page-1)*size;
            if(page>1){
                prePage=1;//表示有前一页
            }
            if(page<pageCount){
                nextPage=1;//表示有下一页
            }
            map.put("beginIndex",beginIndex);
            map.put("size",size);
            userList=tbUserMapper.getAllUserListIndex(map);//获得当页查看到的退款单
            map2.put("page",page);
        }else{
            map2.put("page",1);
        }
        Integer pageSize[]=new Integer[pageCount];
        map2.put("userList",userList);
        map2.put("pageCount",pageCount);
        map2.put("prePage",prePage);
        map2.put("nextPage",nextPage);
        map2.put("pageSize",pageSize);
        map2.put("dataSize",users.size());
        return map2;
    }

    /**
    * @Description: 验证用户名字
    * @Param: [userName]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    @Override
    public String validateName(String userName) {
        TbUser user= null;//存查看到的用户
        user=tbUserMapper.getUserByName(userName);//根据名字查看用户
        if(user!=null){
            return "isExit";
        }
        return "isNotExit";

    }


    /**
    * @Description: 根据用户id查看用户
    * @Param: [userId]
    * @return: cn.com.connext.oms.entity.TbUser
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    @Override
    public TbUser getUserById(Integer userId) {
        return tbUserMapper.getUserById(userId);
    }
}
