package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbReturnGoods;
import cn.com.connext.oms.mapper.TbReturnGoodsMapper;
import cn.com.connext.oms.service.TbReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: OMS4Intern
 * @description: 退货商品逻辑业务层
 * @author: Lili.Chen
 * @create: 2019-01-23 15:44
 **/

@Service
public class TbReturnGoodsServiceImpl implements TbReturnGoodsService {

    @Autowired
    private TbReturnGoodsMapper tbReturnGoodsMapper;



    @Override
    public List<TbReturnGoods> getListReturnGoodsByOrderId(Integer orderId) {
        return tbReturnGoodsMapper.getListReturnGoodsByOrderId(orderId);
    }
}
