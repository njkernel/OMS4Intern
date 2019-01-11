package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.service.TbGoodsListService;
import cn.com.connext.oms.service.TbUpdateStockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Title: TbUpdateStockController</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/8
 */
@RestController
public class TbUpdateStockController {
    @Autowired
    private TbUpdateStockService tbUpdateStockService;
    @Autowired
    private TbGoodsListService tbGoodsListService;
    /**
        * @Author: zhaojun
        * @Description: 更新商品库存
        * @Param: []
        * @Return:
        * @Create: 2019/1/8 11:50
        */
    @GetMapping("updateStock")
    @ApiOperation(value = "库存变更接口")
    public BaseResult updateStock(List<String> tbGoodsCode, List<Integer> tbStocksTotal){
        try {
            for (int i =0;i<tbGoodsCode.size();i++){
                int id = this.tbGoodsListService.findIdByCode(tbGoodsCode.get(i));
                this.tbUpdateStockService.updateStock(id,tbStocksTotal.get(i));
            }
            return BaseResult.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }



    /**
        * @Author: zhaojun
        * @Description: 更新可用库存与锁定库存
        * @Param: []
        * @Create: 2019/1/8 15:50
        */
    @GetMapping("updateLockdAndAvailable")
    @ApiOperation(value = "锁定库存，可用库存变更接口")
    public BaseResult updateLockdAndAvailable(int goodsId, int num, int num2){

        try {

            this.tbUpdateStockService.updateLockdAndAvailable(goodsId,num,num2);
            return BaseResult.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }
}
