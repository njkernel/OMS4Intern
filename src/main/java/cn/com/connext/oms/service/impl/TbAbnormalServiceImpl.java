package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.AbnormalGoodsOrderDTO;
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

import javax.servlet.http.HttpSession;
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

    @Autowired
    private TbExchangeMapper tbExchangeMapper;


    /**
     * @Author: caps
     * @Description:订单预检
     * @Param: [orderId]
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 17:51
     */

    public BaseResult checkGoods(int orderId,HttpSession session){
        List<String> list=new LinkedList<>();
        //判断有无备注或者金额异常
        Example example=new Example(TbOrder.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        TbOrder tbOrder = tbOrderMapper.selectOneByExample(example);

        if (!StringUtils.equals(tbOrder.getOrderState(),"待预检")&&!StringUtils.equals(tbOrder.getOrderState(),"订单异常")){
            return BaseResult.fail("当前订单状态不可以进行预检操作");
        }
        if (StringUtils.equals(tbOrder.getOrderState(),"订单异常")){
            return BaseResult.fail("请先处理当前订单的异常");
        }

        //根据订单id获取相关商品id集合
        List<Integer> goodsIdByOrder = getGoodsIdByOrder(orderId);
        //金额异常
        if(tbOrder.getSumPrice()>15000){
            creatAbnormal(orderId,"金额异常","金额超过一万五","待处理",session);
            list.add("金额异常");
        }
        //备注异常
        if (!StringUtils.equals(tbOrder.getRemark(),"无")){
            creatAbnormal(orderId,"备注异常","备注异常","待处理",session);
            list.add("备注异常");
        }
        //判断库存
        for (Integer goodId:goodsIdByOrder){
            TbStock tbStock = tbAbnormalMapper.selectStockByGoodsId(goodId);
            if(tbStock.getAvailableStock()<=0){
                creatAbnormal(orderId,"库存异常","库存不足","待处理",session);
                list.add("库存异常");
            }
        }
        if (list.size()>0) {
            tbOrder.setOrderState("订单异常");
            tbOrder.setUpdated(new Date());
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
            return BaseResult.fail("订单存在异常，请先进行处理", list);
        }
        else {
            //更改库存信息
            this.lockStock(orderId);
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

    public void creatAbnormal(Integer orderId, String setAbnormalType, String setAbnormalReason, String setAbnormalState, HttpSession session){
        TbAbnormal tbAbnormal=new TbAbnormal();
        tbAbnormal.setOrderId(orderId);
        tbAbnormal.setAbnormalType(setAbnormalType);
        tbAbnormal.setAbnormalReason(setAbnormalReason);
        tbAbnormal.setAbnormalState(setAbnormalState);
        tbAbnormal.setCreatetime(new Date());
        String omsuser = (String) session.getAttribute("OMSUSER");
        tbAbnormal.setModifiedUser(omsuser);
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

        PageHelper.startPage(currentPage,pageSize);
        Example example=new Example(TbAbnormal.class);
        example.createCriteria()
                .andLike("abnormalState",abnormalState!=null?"%"+abnormalState+"%":null)
                .andLike("orderId",orderId!=null?"%"+orderId+"%":null)
                .andLike("abnormalType",abnormalType!=null?"%"+abnormalType+"%":null);
        example.setOrderByClause("createtime DESC");
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
        List<AbnormalGoodsOrderDTO> goods=null;
        Integer orderId = tbAbnormal.getOrderId();
        List<Integer> goodsIdByOrder = getGoodsIdByOrder(orderId);
        double orderTotleprice=0;
        double goodsTotleprice=0;
        for (Integer id:goodsIdByOrder){
            goods = tbAbnormalMapper.getAbnormalGoodsOrderDTOByOrderId(orderId);
        }
        for (AbnormalGoodsOrderDTO abnormalGoodsOrderDTO:goods){
            goodsTotleprice=abnormalGoodsOrderDTO.getTotalPrice()*abnormalGoodsOrderDTO.getNum();
            orderTotleprice+=goodsTotleprice;
        }
        Map<String,Object> map=new HashMap<>();
        map.put("abnormalInfo",abnormals);
        map.put("goodsInfo",goods);
        map.put("totleprice",orderTotleprice);
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
                //更改库存信息
                this.lockStock(orderId);
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
                //判断库存
                //根据订单id获取相关商品id集合
                List<Integer> goodsIdByOrder = getGoodsIdByOrder(orderId);
                //判断库存
                for (Integer goodId:goodsIdByOrder){
                    TbStock tbStock = tbAbnormalMapper.selectStockByGoodsId(goodId);
                    if(tbStock.getAvailableStock()<=0){
                        return BaseResult.fail("库存不足，请通知管理员进货先进货");
                    }
                }
                //更改库存信息
                this.lockStock(orderId);
                //更改订单状态为待路由
                TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
                tbOrder.setOrderState("待路由");
                tbOrderMapper.updateByPrimaryKey(tbOrder);
                //更改异常单状态为已处理
                tbAbnormal.setAbnormalState("已处理");
                tbAbnormalMapper.updateByPrimaryKey(tbAbnormal);
                return BaseResult.success("异常处理成功");
            }
        }
        return BaseResult.fail("当前异常已处理");
    }

    /**
    * @Author: caps
    * @Description:锁定对应的商品库存
    * @Param: [orderId]
    * @Return: void
    * @Create: 2019/1/24 10:24
    */
    public void lockStock(Integer orderId){

        List<Integer> goodsIdByOrder = getGoodsIdByOrder(orderId);
        for (Integer goodsId : goodsIdByOrder) {
            Example example=new Example(TbGoodsOrder.class);
            example.createCriteria().andEqualTo("goodsId",goodsId)
                                    .andEqualTo("orderId",orderId);
            TbGoodsOrder tbGoodsOrder = tbGoodsOrderMapper.selectOneByExample(example);
            //更改锁定库存
            Integer num = tbGoodsOrder.getNum();
            TbStock tbStock = tbAbnormalMapper.selectStockByGoodsId(goodsId);
            tbStock.setLockedStock(num);
            //更改可用库存
            Integer availableStock = tbStock.getAvailableStock();
            availableStock-=num;
            tbStock.setAvailableStock(availableStock);
            tbStockMapper.updateByPrimaryKeySelective(tbStock);
        }

    }



}

