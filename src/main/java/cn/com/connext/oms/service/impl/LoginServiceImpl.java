package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbPermission;
import cn.com.connext.oms.entity.TbRole;
import cn.com.connext.oms.entity.TbRolePermission;
import cn.com.connext.oms.entity.TbUser;
import cn.com.connext.oms.mapper.TbPermissionMapper;
import cn.com.connext.oms.mapper.TbRoleMapper;
import cn.com.connext.oms.mapper.TbRolePermissionMapper;
import cn.com.connext.oms.mapper.TbUserMapper;
import cn.com.connext.oms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.management.relation.Role;
import java.util.List;

/**
 * <p>Title: LoginServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/13 15:33
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;
    /**
    * @Author: caps
    * @Description:根据用户名获取角色
    * @Param: [userName]
    * @Return: cn.com.connext.oms.entity.TbUser
    * @Create: 2019/1/13 17:05
    */

    @Override
    public TbUser findUserByName(String userName) {
        Example example=new Example(TbUser.class);
        example.createCriteria().andEqualTo("userName",userName);
        TbUser tbUser = tbUserMapper.selectOneByExample(example);
        return tbUser;
    }

    /**
    * @Author: caps
    * @Description: 获取所有角色
    * @Param: []
    * @Return: java.util.List<cn.com.connext.oms.entity.TbRole>
    * @Create: 2019/1/13 17:05
    */

    @Override
    public List<TbRole> getAllRole() {
        return tbRoleMapper.selectAll();
    }

    /**
    * @Author: caps
    * @Description:根据角色id查询用户是否存在
    * @Param: [roleId]
    * @Return: java.util.List<cn.com.connext.oms.entity.TbUser>
    * @Create: 2019/1/13 17:37
    */

    @Override
    public TbUser getUserByRoleId(String userName,Integer roleId) {
        Example example=new Example(TbUser.class);
        example.createCriteria().andEqualTo("roleId",roleId)
                                .andEqualTo("userName",userName);
        TbUser tbUser = tbUserMapper.selectOneByExample(example);
        return tbUser;
    }


    /**
     * @Author: caps
     * @Description:查询用户权限
     * @Param: [userName]
     * @Return: javax.management.relation.Role
     * @Create: 2019/1/13 18:46
     */
    @Override
    public List<TbPermission> getPermissionByUserName(String userName) {
        //查询用户角色id
        Example example=new Example(TbUser.class);
        example.createCriteria().andEqualTo("userName",userName);
        TbUser tbUser = tbUserMapper.selectOneByExample(example);
        Integer roleId = tbUser.getRoleId();
        //根据角色id查询用户权限
        List<TbPermission> permissionsByRoleId = tbPermissionMapper.getPermissionsByRoleId(roleId);
        return permissionsByRoleId;
    }
}
