package cn.com.connext.oms.commons.dto;

/**
 * <p>Title: GoodsDTO</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/10
 */
public class GoodsDTO {
    private String sku;
    private Double goodsPrice;
    private String goodsName;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public GoodsDTO(String sku, Double goodsPrice, String goodsName) {
        this.sku = sku;
        this.goodsPrice = goodsPrice;
        this.goodsName = goodsName;
    }

    public GoodsDTO(){

    }
}
