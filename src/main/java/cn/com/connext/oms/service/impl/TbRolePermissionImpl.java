package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbPermission;
import cn.com.connext.oms.mapper.TbRolePermissionMapper;
import cn.com.connext.oms.service.TbRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TbRolePermissionImpl implements TbRolePermissionService {
    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public List<TbPermission> getPermissionsByRole(Integer roleId) {
        return tbRolePermissionMapper.getPermissionsByRole(roleId);
    }
}
