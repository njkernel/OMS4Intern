package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbStock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
@Repository
public interface TbStockMapper extends MyMapper<TbStock> {
    /**
     * @Author: zhaojun
     * @Description: 更新商品的库存信息
     * @Param:
     * @Return:
     * @Create: 2019/1/8 11:14
     */
    /*List<TbStock> updateStock(int goodsId);*/
    public void updateStock(int goodsId,int totalStock);
    /**
     * @Author: zhaojun
     * @Description:
     * @Param: []
     * @Return:
     * @Create: ${DATE}
     */
    public void updateLockdAndAvailable(int goodsId,int num,int num2);
}