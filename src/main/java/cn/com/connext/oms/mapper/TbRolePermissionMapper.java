package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbPermission;
import cn.com.connext.oms.entity.TbRolePermission;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbRolePermissionMapper extends MyMapper<TbRolePermission> {
    List<TbPermission> getPermissionsByRole(Integer roleId);
}

