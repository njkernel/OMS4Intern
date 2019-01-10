package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.GoodsStockDto;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.mapper.TbGoodsMapper;
import cn.com.connext.oms.mapper.TbGoodsOrderMapper;
import cn.com.connext.oms.service.TbGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>Title: TbGoodsListServiceImp</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/7
 */
@Service
public class TbGoodsListServiceImpl implements TbGoodsListService {
    @Autowired
    private TbGoodsMapper tbGoodsMapper;
    @Autowired
    private TbGoodsOrderMapper tbGoodsOrderMapper;

    /**
     * @Author: zhaojun
     * @Description: 根据订单编号查询对应的商品编号，再根据商品编号查询该订单的商品列表
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 13:38
     */
    @Override
    public List<TbGoods> getGoodsImformation(Integer orderId) {
        List<Integer> list = tbGoodsOrderMapper.getGoodOrderById(orderId);
        List tbGoods=new LinkedList<>();
        for (Integer integer:list){
            List<TbGoods> goods = tbGoodsMapper.getGoodsImformation(integer);
            tbGoods.add(goods);
        }
        return tbGoods;
    }
    /**
        * @Author: zhaojun
        * @Description: 查询库存所有商品
        * @Param: []
        * @Create: 2019/1/8 16:54
        */

    @Override
    public List<GoodsStockDto> getAllGoods() {
        return this.tbGoodsMapper.getAllGoods();
    }

    /**
        * @Author: zhaojun
        * @Description: 根据商品的code查询商品的ID
        * @Param: []
        * @Create: 2019/1/7 17:54
        */
    @Override
    public int findIdByCode(String goodsCode) {
        return this.tbGoodsMapper.findIdByCode(goodsCode);
    }
}
