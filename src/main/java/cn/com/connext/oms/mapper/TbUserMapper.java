package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
public interface TbUserMapper extends MyMapper<TbUser> {
}