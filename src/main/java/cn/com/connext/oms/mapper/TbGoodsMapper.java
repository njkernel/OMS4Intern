package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.GoodsStockDto;
import cn.com.connext.oms.entity.TbGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbGoodsMapper extends MyMapper<TbGoods> {
    /**
     * @Author: zhaojun
     * @Description: 根据商品编号查询该商品详细信息
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 13:30
     */
    List<TbGoods> getGoodsImformation(int goodId);
    /**
     * @Author: zhaojun
     * @Description: 查询所有商品
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 16:30
     */
    List<GoodsStockDto>getAllGoods();
    /**
     * @Author: zhaojun
     * @Description: 根据商品id查询商品code
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2018/1/19 13:03
     */
    public Integer findIdByCode(String goodsCode);
    /**
     * @Author: zhaojun
     * @Description: 根据商品id修改商品的信息
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2018/1/9 16:03
     */
    public void updateGoods(String goodsCode,String goodsName,Double goodsPrice);
    /**
     * @Author: zhaojun
     * @Description: 商品列表的模糊查询
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2018/1/10 15:40
     */
    public List<TbGoods> fuzzySearch(String mark);
    /**
     * 功能描述:分类模糊查询
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/18 10:46
     */
    public List<GoodsStockDto> selectAllGoods(@Param("goodsStockDto")GoodsStockDto goodsStockDto);


    /**
     * 功能描述:新增商品
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/25 11:26
     */
    public void addGoods (String goodsCode,String goodsName,Double goodsPrice);
}