package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.service.TbGoodsListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: fuzzySearchGoodsController</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/10
 */
@RestController
public class TbFuzzySearchGoodsController {
    @Autowired
    private TbGoodsListService tbGoodsListService;
    /**
     *
     * 功能描述:
     *
     * @param: [mark]
     * @return: cn.com.connext.oms.commons.dto.BaseResult
     * @auther: Jun.Zhao
     * @date: 2019/1/10 17:01
     */
    @GetMapping("fuzzySearch")
    public BaseResult fuzzySearch(String mark){
        try {
            PageHelper.startPage(1,5);
            List<TbGoods> fuzzySearchGoods =this.tbGoodsListService.fuzzySearch(mark);
            PageInfo<TbGoods> pageInfo = new PageInfo<>(fuzzySearchGoods);
            return BaseResult.success("成功",pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("系统内部错误");
        }
    }
}
