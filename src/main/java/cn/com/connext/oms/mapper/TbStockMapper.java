package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbStock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbStockMapper extends MyMapper<TbStock> {

    /** 
    * @Description: 更新库存 
    * @Param: [tbStock] 
    * @return: int 
    * @Author: Lili Chen 
    * @Date: 2019/1/11 
    */
   int updateStock(TbStock tbStock);


   /**
   * @Description: 根据商品id查看库存
   * @Param: [goodsId]
   * @return: cn.com.connext.oms.entity.TbStock
   * @Author: Lili Chen
   * @Date: 2019/1/11
   */
   TbStock getStockByGoodsId(Integer goodsId);
   
   
   /** 
   * @Description: 批量更新库存 
   * @Param: [stockList] 
   * @return: int 
   * @Author: Lili Chen 
   * @Date: 2019/1/11 
   */
   int updateStockList(List<TbStock> stockList);
}