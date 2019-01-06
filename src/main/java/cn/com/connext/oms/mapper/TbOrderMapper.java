package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbOrderMapper extends MyMapper<TbOrder> {
    /**
    * @Author: caps
    * @Description:
    * @Param: []
    * @Return: java.util.List<cn.com.connext.oms.entity.TbOrder>
    * @Create: 2019/1/6 10:14
    */
    List<TbOrder> getAllOrder();
}