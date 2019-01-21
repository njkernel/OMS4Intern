package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbPermission;

import java.util.List;

public interface TbRolePermissionService {
    List<TbPermission> getPermissionsByRole(Integer roleId);
}
