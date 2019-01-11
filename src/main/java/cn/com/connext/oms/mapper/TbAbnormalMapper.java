package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbAbnormal;
import cn.com.connext.oms.entity.TbStock;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

public interface TbAbnormalMapper extends MyMapper<TbAbnormal> {
    TbStock selectStockByGoodsId(@Param("goodsId") int goodsId);
}