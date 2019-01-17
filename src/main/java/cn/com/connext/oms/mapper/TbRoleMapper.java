package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbRoleMapper extends MyMapper<TbRole> {


    /**
    * @Description: 根据角色id查看角色
    * @Param: [roleId]
    * @return: cn.com.connext.oms.entity.TbRole
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    TbRole getRoleById(Integer roleId);


    /**
    * @Description: 查看所有的角色
    * @Param: []
    * @return: cn.com.connext.oms.entity.TbRole
    * @Author: Lili Chen
    * @Date: 2019/1/17
    */
    List<TbRole> getListRole();
}