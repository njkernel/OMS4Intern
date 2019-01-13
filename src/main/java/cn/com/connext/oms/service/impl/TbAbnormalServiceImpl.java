package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.utils.StringUtils;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.*;
import cn.com.connext.oms.service.TbAbnormalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * <p>Title: TbAbnormalServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/7 10:58
 */
@Service
public class TbAbnormalServiceImpl implements TbAbnormalService {
    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsOrderMapper tbGoodsOrderMapper;

    @Autowired
    private TbStockMapper tbStockMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbAbnormalMapper tbAbnormalMapper;


    /**
     * @Author: caps
     * @Description:订单预检
     * @Param: [orderId]
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 17:51
     */

    public BaseResult checkGoods(int orderId){



        List<String> list=new LinkedList<>();
        //判断有无备注或者金额异常
        Example example=new Example(TbOrder.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        TbOrder tbOrder = tbOrderMapper.selectOneByExample(example);

        if (!StringUtils.equals(tbOrder.getOrderState(),"待预检")){
            return BaseResult.fail("当前订单状态不可以进行预检操作");
        }

        //根据订单id获取相关商品id集合
        List<Integer> goodsIdByOrder = getGoodsIdByOrder(orderId);
        //金额异常
        if(tbOrder.getSumPrice()>15000){
            creatAbnormal(orderId,"金额异常","金额超过一万五","待处理");
            list.add("金额异常");
        }
        //备注异常
        if (!StringUtils.equals(tbOrder.getRemark(),"无")){
            creatAbnormal(orderId,"备注异常","备注异常","待处理");
            list.add("备注异常");
        }
        //判断库存
        for (Integer goodId:goodsIdByOrder){
            TbStock tbStock = tbAbnormalMapper.selectStockByGoodsId(goodId);
            if(tbStock.getAvailableStock()<=0){
                creatAbnormal(orderId,"库存异常","库存不足","待处理");
                list.add("库存异常");
            }
        }
        if (list.size()>0) {
            tbOrder.setOrderState("订单异常");
            tbOrder.setUpdated(new Date());
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
            return BaseResult.fail("异常", list);
        }
        else {
            tbOrder.setOrderState("待路由");
            tbOrder.setUpdated(new Date());
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
            return BaseResult.success("预检通过");
        }

    }

    /**
     * @Author: caps
     * @Description:根据订单id获取商品id
     * @Param: [orderId]
     * @Return: java.util.List<java.lang.Integer>
     * @Create: 2019/1/7 17:51
     */

    public List<Integer> getGoodsIdByOrder(int orderId){
        List<Integer> list=new LinkedList();
        Example example=new Example(TbGoodsOrder.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        List<TbGoodsOrder> tbGoodsOrders = tbGoodsOrderMapper.selectByExample(example);
        for (TbGoodsOrder tbGoodsOrder:tbGoodsOrders){
            list.add(tbGoodsOrder.getGoodsId());
        }
        return list;
    }


    /**
     * @Author: caps
     * @Description:生成异常订单
     * @Param: [orderId, setAbnormalType, setAbnormalReason, setAbnormalState]
     * @Return: void
     * @Create: 2019/1/7 17:51
     */

    public void creatAbnormal(Integer orderId,String setAbnormalType,String setAbnormalReason,String setAbnormalState){
        TbAbnormal tbAbnormal=new TbAbnormal();
        tbAbnormal.setOrderId(orderId);
        tbAbnormal.setAbnormalType(setAbnormalType);
        tbAbnormal.setAbnormalReason(setAbnormalReason);
        tbAbnormal.setAbnormalState(setAbnormalState);
        tbAbnormal.setCreatetime(new Date());
        tbAbnormalMapper.insert(tbAbnormal);
    }

    /**
     * @Author: caps
     * @Description:异常订单列表加模糊查询
     * @Param: [currentPage, pageSize, tbAbnormal]
     * @Return: com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbAbnormal>
     * @Create: 2019/1/7 17:50
     */
    @Transactional(readOnly = true)
    public PageInfo<TbAbnormal> checkList(Integer currentPage, Integer pageSize, TbAbnormal tbAbnormal){
        String abnormalState = tbAbnormal.getAbnormalState();
        Integer orderId = tbAbnormal.getOrderId();
        String abnormalType = tbAbnormal.getAbnormalType();
        String modifiedUser = tbAbnormal.getModifiedUser();

        PageHelper.startPage(currentPage,pageSize);
        Example example=new Example(TbAbnormal.class);
        example.createCriteria()
                .andLike("abnormalState",abnormalState!=null?"%"+abnormalState+"%":null)
                .andLike("orderId",orderId!=null?"%"+orderId+"%":null)
                .andLike("abnormalType",abnormalType!=null?"%"+abnormalType+"%":null)
                .andLike("modifiedUser",modifiedUser!=null?"%"+modifiedUser+"%":null);
        List<TbAbnormal> tbAbnormals = tbAbnormalMapper.selectByExample(example);

        PageInfo<TbAbnormal> pageInfo=new PageInfo<>(tbAbnormals);
        return pageInfo;
    }

    /**
     * @Author: caps
     * @Description:异常订单详情
     * @Param: [abnormalId]
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/8 10:55
     */

    public Map<String,Object> abnormalDetail(int abnormalId){
        List<TbAbnormal> abnormals=new LinkedList<>();
        //获取异常订单详情
        TbAbnormal tbAbnormal = tbAbnormalMapper.selectByPrimaryKey(abnormalId);
        abnormals.add(tbAbnormal);
        //获取异常订单对应商品详情
        List<TbGoods> goods=new LinkedList<>();
        Integer orderId = tbAbnormal.getOrderId();
        List<Integer> goodsIdByOrder = getGoodsIdByOrder(orderId);
        for (Integer id:goodsIdByOrder){
            TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(id);
            goods.add(tbGoods);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("abnormalInfo",abnormals);
        map.put("goodsInfo",goods);
        return map;
    }

    /**
     * @Author: caps
     * @Description: 异常处理
     * @Param: [abnormalId]
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/8 13:09
     */

    public BaseResult abnormalHandle(int abnormalId){
        TbAbnormal tbAbnormal = tbAbnormalMapper.selectByPrimaryKey(abnormalId);
        Integer orderId = tbAbnormal.getOrderId();
        String abnormalState = tbAbnormal.getAbnormalState();
        if (StringUtils.equals(abnormalState,"待处理")) {
            if(StringUtils.equals(tbAbnormal.getAbnormalType(),"备注异常")||StringUtils.equals(tbAbnormal.getAbnormalType(),"金额异常")){
                //更改订单状态为待路由
                TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
                tbOrder.setOrderState("待路由");
                tbOrderMapper.updateByPrimaryKey(tbOrder);
                //更改异常单状态为已处理
                tbAbnormal.setAbnormalState("已处理");
                tbAbnormalMapper.updateByPrimaryKey(tbAbnormal);
                return BaseResult.success("异常处理成功");
            }
            if(StringUtils.equals(tbAbnormal.getAbnormalType(),"库存异常")){
                return BaseResult.fail("库存不足，请通知管理员进货先进货");
            }
        }
        return BaseResult.fail("当前异常已处理");
    }


}

