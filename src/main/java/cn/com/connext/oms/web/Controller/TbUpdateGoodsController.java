package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.GoodsDTO;
import cn.com.connext.oms.service.TbGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: TbUpdateGoodsController</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/10
 */
@RestController
public class TbUpdateGoodsController {
    @Autowired
    private TbGoodsListService tbGoodsListService;
    /**
     * 功能描述:
     * @param: [tbGoods]
     * @return: cn.com.connext.oms.commons.dto.BaseResult
     * @auther: Jun.Zhao
     * @date: 2019/1/9 17:07
     */
    @PostMapping("updateGoods")
    public BaseResult updateGoods(@RequestBody  List<GoodsDTO> tbGoods){
        try {
            for (int i=0;i<tbGoods.size();i++) {
                this.tbGoodsListService.updateGoods(tbGoods.get(i).getSku(), tbGoods.get(i).getGoodsName(), tbGoods.get(i).getGoodsPrice());
            }
            return BaseResult.success("成功");
        } catch (Exception e) {
                e.printStackTrace();
                return BaseResult.fail("系统内部错误");
        }
    }
}
