package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbRole;
import cn.com.connext.oms.mapper.TbRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TbRoleService {

    List<TbRole> getListRole();


}
