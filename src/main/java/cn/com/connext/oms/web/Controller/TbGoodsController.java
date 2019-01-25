package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.GoodsDTO;
import cn.com.connext.oms.commons.dto.GoodsGoodsOrderDto;
import cn.com.connext.oms.commons.dto.GoodsStockDto;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.mapper.TbGoodsMapper;
import cn.com.connext.oms.mapper.TbStockMapper;
import cn.com.connext.oms.service.TbGoodsListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: TbGoodsController</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/16
 */
@RestController
public class TbGoodsController {
    @Autowired
    private TbGoodsListService tbGoodsListService;
    @Autowired
    private TbGoodsMapper tbGoodsMapper;
    @Autowired
    private TbStockMapper tbStockMapper;
    /**
     *
     * 功能描述:模糊查询
     *
     * @param: [mark]
     * @return: cn.com.connext.oms.commons.dto.BaseResult
     * @auther: Jun.Zhao
     * @date: 2019/1/10 17:01
     */
    @GetMapping("fuzzySearch")
    public BaseResult fuzzySearch(String mark) {
        try {
            PageHelper.startPage(1, 5);
            List<TbGoods> fuzzySearchGoods = this.tbGoodsListService.fuzzySearch(mark);
            PageInfo<TbGoods> pageInfo = new PageInfo<>(fuzzySearchGoods);
            return BaseResult.success("成功", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("系统内部错误");
        }
    }

    /**
     * 功能描述:
     * @param: [tbGoods]
     * @return: cn.com.connext.oms.commons.dto.BaseResult
     * @auther: Jun.Zhao
     * @date: 2019/1/9 17:07
     */
    @PostMapping("updateGoods")
    public BaseResult updateGoods(@RequestBody List<GoodsDTO> tbGoods){
        try {
            for (int i=0;i<tbGoods.size();i++) {
                if(tbGoods.get(i).getSku()==null||tbGoods.get(i).getGoodsName()==null||tbGoods.get(i).getGoodsPrice()==null) {
                    return BaseResult.fail("商品信息有缺失");
                }else{
                    /*查询数据库中是否有该商品*/
                    if (this.tbGoodsListService.findIdByCode(tbGoods.get(i).getSku())==null){
                        /*无该商品，在商品表插入新商品*/
                        this.tbGoodsListService.addGoods(tbGoods.get(i).getSku(),tbGoods.get(i).getGoodsName(),tbGoods.get(i).getGoodsPrice());
                        /*无该商品 创建库存表信息，并等待库存同步*/
                        Integer id =this.tbGoodsListService.findIdByCode(tbGoods.get(i).getSku());
                        /*设置商品对应的锁定库存（不设置后面更新库存时会报空指针异常），和商品对应的id*/
                        this.tbStockMapper.addStock(0,id);
                    }else{
                        /*有该商品直接更新*/
                        this.tbGoodsListService.updateGoods(tbGoods.get(i).getSku(), tbGoods.get(i).getGoodsName(), tbGoods.get(i).getGoodsPrice());
                    }
                }
            }
            return BaseResult.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("系统内部错误");
        }
    }
    /**
     * @Author: ZhaoJun
     * @Description: 根据订单编号查询该订单的商品编号
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 11:51
     */
    @GetMapping("getGoodsList")
    @ApiOperation(value = "订单商品列表接口")
    public BaseResult GetGoodsList(int orderId){
        try {
            List<TbGoods> allGoods = tbGoodsListService.getGoodsImformation(orderId);
            return BaseResult.success("成功",allGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("失败");
        }
    }
    /**
     * @Author: ZhaoJun
     * @Description: 查询所有商品的信息和库存
     * @Param: []
     * @Create: 2019/1/7 17:36
     */
    /*@GetMapping("getAllGoods")
    @ApiOperation(value = "查询所有商品的信息和库存")
    public BaseResult getAllGoods(int pageNum,int pageSize){
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<GoodsStockDto> getAllGoods =this.tbGoodsListService.getAllGoods();
            PageInfo<GoodsStockDto> goodsListInfo = new PageInfo<>(getAllGoods);
            return BaseResult.success("成功",goodsListInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }*/
    /**
     * 功能描述:显示所有商品和模糊查询
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/18 11:13
     */
    @RequestMapping("/getAllGoods")
    @ApiOperation(value = "显示所有商品接口")
    public  BaseResult showAllGoods(Integer currentPage,Integer pageSize,GoodsStockDto goodsStockDto){
        try {
            PageInfo<GoodsStockDto> pageInfo=tbGoodsListService.getAllGoods(currentPage,pageSize,goodsStockDto);
            return BaseResult.success("查询成功！",pageInfo);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return BaseResult.fail("服务器内部错误！");
        }
    }
    /**
     * @Author: ZhaoJun
     * @Description: 根据订单号查询订单对应的商品信息
     * @Param: []
     * @Return:
     * @Create: 2019/1/8 17:36
     */
    @GetMapping("goodsListFromOrder")
    public BaseResult goodsListFromOrder(int orderId){
        try {
            List<GoodsGoodsOrderDto> goodsListFromOrder= tbGoodsListService.goodsListFromOrder(orderId);
            return BaseResult.success("成功",goodsListFromOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }
}
