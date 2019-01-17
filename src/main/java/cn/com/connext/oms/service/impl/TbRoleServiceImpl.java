package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbRole;
import cn.com.connext.oms.mapper.TbRoleMapper;
import cn.com.connext.oms.service.TbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: OMS4Intern
 * @description: 角色的业务逻辑层
 * @author: Lili.Chen
 * @create: 2019-01-17 14:49
 **/

@Service
public class TbRoleServiceImpl implements TbRoleService {

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Override
    public List<TbRole> getListRole() {
        return tbRoleMapper.getListRole();
    }
}
