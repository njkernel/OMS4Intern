package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.mapper.TbRefundMapper;
import cn.com.connext.oms.service.TbRefundService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: OMS4Intern
 * @description: 退款的业务操作
 * @author: Lili.Chen
 * @create: 2019-01-08 15:57
 **/
@Service
public class TbRefundServiceImpl implements TbRefundService {
    @Autowired
    private TbRefundMapper tbRefundMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;


    /**
    * @Description: 退款操作
    * @Param: [tbRefundList]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2019/1/8
    */
    @Override
    public boolean updateRefundListStatue(Integer[] refundIdList) {
        List<TbRefund> refundList=new ArrayList<TbRefund>();//保存需要修改的退款单
        int refundResult=0;//保存修改退款单的条数
        for(Integer refundId:refundIdList){
            TbRefund refund=tbRefundMapper.getRefundById(refundId);
            if(refund!=null){//如果存在这张退款单
                if("待退款".equals(refund.getRefundState())){
                    refund.setRefundState("退款成功");
                    refundList.add(refund);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        if(!refundList.isEmpty()){
            refundResult=tbRefundMapper.updateRefundListStatue(refundList);
        }
        if(refundResult==refundList.size()){
            return true;
        }

        return false;
    }

    /** 
    * @Description: 查看所有的退款单（分页操作）
    * @Param: [beginIndex, size] 
    * @return: java.util.List<cn.com.connext.oms.entity.TbRefund> 
    * @Author: Lili Chen 
    * @Date: 2019/1/8 
    */
    @Override
    public Map<String,Object> getAllRefundIndex(Integer page, Integer size) {
        Integer beginIndex=0;//查看数据库退款单列表开始的索引
        List<TbRefund> refunds=tbRefundMapper.getAllRefund();
        if(!refunds.isEmpty()){
            Integer count=refunds.size();//总共的退款单条数
            Integer pageCount=count/4;//总共的页数
            if(count%4!=0){
                pageCount++;
            }
            if(page<1){
                page=1;
            }
            if(page>pageCount){
                page=pageCount;
            }
            beginIndex=(page-1)*size;
            Map map=new HashMap<>();//存查看退款单分页的参数
            map.put("beginIndex",beginIndex);
            map.put("size",size);
            List<TbRefund> refundList=tbRefundMapper.getAllRefundIndex(map);//获得当页查看到的退款单
            Map map2=new HashMap<>();//存返回值
            map2.put("refundList",refundList);
            map2.put("page",page);
            map2.put("pageCount",pageCount);
            return map2;
        }
       return null;
    }

    /**
    * @Description: 根据id查看退款单
    * @Param: [refundId]
    * @return: cn.com.connext.oms.entity.TbRefund
    * @Author: Lili Chen
    * @Date: 2019/1/10
    */
    @Override
    public TbRefund getRefundById(Integer refundId) {
        return tbRefundMapper.getRefundById(refundId);
    }


    /**
    * @Description: 根据退款单状态查看退款单
    * @Param: [refundState]
    * @return: java.util.Map
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    @Override
    public Map getListRefundByState(String refundState,Integer page, Integer size) {
        Map map=new HashMap<>();//存查看退款单分页的参数
        Integer pageCount=1;//总页数
        PageHelper.startPage(page,size);
        List<TbRefund> tbRefundList = tbRefundMapper.getListRefundByState(refundState);//根据退款状态查看退款单
        PageInfo<TbRefund> pageInfo=new PageInfo<>(tbRefundList);
        pageCount=pageInfo.getPages();//获取总页数
        if(pageCount==0){
            pageCount=1;
        }
        if(page>pageCount){//如果目的页面大于总页数
            page=pageCount;
            PageHelper.startPage(page,size);
            tbRefundList = tbRefundMapper.getListRefundByState(refundState);
        }else if(page==0){//如果目的页面等于0
            page=1;
            PageHelper.startPage(page,size);
            tbRefundList = tbRefundMapper.getListRefundByState(refundState);
        }
        map.put("refundList",tbRefundList);
        map.put("page",page);
        map.put("pageCount",pageCount);
        return map;

    }

    /**
    * @Description: 根据退款单的订单id查看退款单
    * @Param: [orderId]
    * @return: java.util.Map
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    @Override
    public Map getListRefundByOrderCode(String orderCode,Integer page, Integer size) {
        Map map=new HashMap<>();//存查看退款单分页的参数
        List<TbRefund> tbRefundList =new ArrayList<>();
        TbOrder order=tbOrderMapper.getOrderByCode(orderCode);//根据订单编码得到相应的订单
        PageHelper.startPage(page,size);//利用pageInfo
        if(order!=null){//如果存在这个订单
            tbRefundList = tbRefundMapper.getListRefundByOrderId(order.getOrderId());//根据订单号得到退款单列表
        }else{//如果相应的订单为空
            map.put("page",1);
            map.put("pageCount",1);
            return map;
        }
        PageInfo<TbRefund> pageInfo=new PageInfo<>(tbRefundList);
        if(page>pageInfo.getPages()){//如果当前页大于总页数
            page=pageInfo.getPages();
            PageHelper.startPage(page,size);
            tbRefundList = tbRefundMapper.getListRefundByOrderId(order.getOrderId());
        }else if(page==0){//如果页数为0
            page=1;
            PageHelper.startPage(page,size);
            tbRefundList = tbRefundMapper.getListRefundByOrderId(order.getOrderId());//根据订单id获得退款单列表
        }
        map.put("refundList",tbRefundList);
        map.put("page",page);
        map.put("pageCount",pageInfo.getPages());
        return map;
    }
}
