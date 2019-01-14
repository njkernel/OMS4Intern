package cn.com.connext.oms.commons.dto;

/**
 * <p>Title: CodeTotalStockDTO</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/9
 */
public class CodeTotalStockDTO {
    private String goodsCode;
    private Integer totalStock;
    /**
     * 获取商品编号
     *
     * @return goods_code - 商品编号
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 设置商品编号
     *
     * @param goodsCode 商品编号
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
    /**
     * 获取总库存
     *
     * @return total_stock - 总库存
     */
    public Integer getTotalStock() {
        return totalStock;
    }

    /**
     * 设置总库存
     *
     * @param totalStock 总库存
     */
    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public CodeTotalStockDTO() {
    }

    public CodeTotalStockDTO(String goodsCode, Integer totalStock) {
        this.goodsCode = goodsCode;
        this.totalStock = totalStock;
    }

}
