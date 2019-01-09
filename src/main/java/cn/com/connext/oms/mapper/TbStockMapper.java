package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface TbStockMapper extends MyMapper<TbStock> {
    TbStock selectStockByGoodsId(@Param("goodsId") int goodsId);
}