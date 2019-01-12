package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.mapper.TbRefundMapper;
import cn.com.connext.oms.service.TbRefundService;
import com.github.pagehelper.PageHelper;
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
       /* for(TbRefund refund:tbRefundList){
            if("待退款".equals(refund.getRefundState())){
                refund.setRefundState("退款成功");
            }else{
                return false;
            }
        }
        if(!refundList.isEmpty()){
            refundResult=tbRefundMapper.updateRefundListStatue(refundList);
        }
        if(refundResult==refundList.size()){
            return true;
        }*/

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
}
