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

import java.util.*;

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
        Date date=new Date();//退款单修改时间
        List<TbRefund> refundList=new ArrayList<TbRefund>();//保存需要修改的退款单
        int refundResult=0;//保存修改退款单的条数
        for(Integer refundId:refundIdList){
            TbRefund refund=tbRefundMapper.getRefundById(refundId);
            if(refund!=null){//如果存在这张退款单
                if("待退款".equals(refund.getRefundState())){
                    refund.setRefundState("退款成功");
                    refund.setUpdated(date);
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
        Map map=new HashMap<>();//存查看退款单分页的参数
        Map map2=new HashMap<>();//存返回值
        Integer beginIndex=0;//查看数据库退款单列表开始的索引
        List<TbRefund> refundList=new ArrayList<>();//存分页中返回的退款单
        Integer prePage=0;//保存是否有前一页
        Integer nextPage=0;//保存是否有后一页
        Integer pageCount=1;
        List<TbRefund> refunds=tbRefundMapper.getAllRefund();
        if(!refunds.isEmpty()){
            Integer count=refunds.size();//总共的退款单条数
            pageCount=count/size;//总共的页数
            if(count%size!=0){
                pageCount++;
            }
            if(page<1){
                page=1;
            }
            if(page>pageCount){
                page=pageCount;
            }
            beginIndex=(page-1)*size;
            if(page>1){
                prePage=1;//表示有前一页
            }
            if(page<pageCount){
                nextPage=1;//表示有下一页
            }
            map.put("beginIndex",beginIndex);
            map.put("size",size);
            refundList=tbRefundMapper.getAllRefundIndex(map);//获得当页查看到的退款单
            map2.put("page",page);
        }else{
            map2.put("page",1);
        }
        Integer pageSize[]=new Integer[pageCount];
        map2.put("refundList",refundList);
        map2.put("pageCount",pageCount);
        map2.put("prePage",prePage);
        map2.put("nextPage",nextPage);
        map2.put("pageSize",pageSize);
        map2.put("dataSize",refunds.size());
        return map2;
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
        Map map2=new HashMap<>();//存返回值
        Integer beginIndex=0;//查看数据库退款单列表开始的索引
        Integer prePage=0;//保存是否有前一页
        Integer nextPage=0;//保存是否有后一页
        Integer pageCount=1;//总页数
        List<TbRefund> refundList=new ArrayList<>();//存分页中返回的退款单
        List<TbRefund> refunds=tbRefundMapper.getListRefundByState(refundState);//根据状态查看退款单
        if(!refunds.isEmpty()){
            Integer count=refunds.size();//总共的退款单条数
            pageCount=count/size;//总共的页数
            if(count%size!=0){
                pageCount++;
            }
            if(page<1){//如果目的页面等于0
                page=1;
            }
            if(page>pageCount){//如果目的页面大于总页数
                page=pageCount;
            }
            beginIndex=(page-1)*size;
            if(page>1){
                prePage=1;//表示有前一页
            }
            if(page<pageCount){
                nextPage=1;//表示有下一页
            }
            map.put("beginIndex",beginIndex);
            map.put("size",size);
            map.put("refundState",refundState);
            refundList=tbRefundMapper.getListRefundByStateIndex(map);//获得当页查看到的退款单
            map2.put("page",page);

        }else{
            map2.put("page",1);
        }
        Integer pageSize[]=new Integer[pageCount];
        map2.put("refundList",refundList);
        map2.put("pageCount",pageCount);
        map2.put("prePage",prePage);
        map2.put("nextPage",nextPage);
        map2.put("pageSize",pageSize);
        map2.put("dataSize",refunds.size());
        return map2;

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
        Map map2=new HashMap<>();//存返回值
        Integer beginIndex=0;//查看数据库退款单列表开始的索引
        Integer prePage=0;//保存是否有前一页
        Integer nextPage=0;//保存是否有后一页
        Integer pageCount=1;//总页数
        List<TbRefund> refundList=new ArrayList<>();//存分页中返回的退款单
        List<TbRefund> refunds =new ArrayList<>();//存该订单id相关的所有的退款单
        TbOrder order=tbOrderMapper.getOrderByCode(orderCode);//根据订单编码得到相应的订单
        if(order!=null){//如果存在这个订单
            refunds = tbRefundMapper.getListRefundByOrderId(order.getOrderId());//根据订单号得到退款单列表
        }
        if(!refunds.isEmpty()){
            Integer count=refunds.size();//总共的退款单条数
            pageCount=count/size;//总共的页数
            if(count%size!=0){
                pageCount++;
            }
            if(page<1){//如果目的页面等于0
                page=1;
            }
            if(page>pageCount){//如果目的页面大于总页数
                page=pageCount;
            }
            beginIndex=(page-1)*size;
            if(page>1){
                prePage=1;//表示有前一页
            }
            if(page<pageCount){
                nextPage=1;//表示有下一页
            }
            map.put("beginIndex",beginIndex);
            map.put("size",size);
            map.put("orderId",order.getOrderId());
            refundList=tbRefundMapper.getListRefundByOrderIdIndex(map);//获得当页查看到的退款单
            map2.put("page",page);

        }else{
            map2.put("page",1);
        }
        Integer pageSize[]=new Integer[pageCount];
        map2.put("refundList",refundList);
        map2.put("pageCount",pageCount);
        map2.put("prePage",prePage);
        map2.put("nextPage",nextPage);
        map2.put("pageSize",pageSize);
        map2.put("dataSize",refunds.size());
        return map2;
    }
}
