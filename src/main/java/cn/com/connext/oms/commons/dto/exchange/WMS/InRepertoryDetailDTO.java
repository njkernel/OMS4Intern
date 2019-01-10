package cn.com.connext.oms.commons.dto.exchange.WMS;

/**
 * @Author: yonyong
 * @Date: 2019/1/8 9:41
 * @Version 1.0
 */

public class InRepertoryDetailDTO {
    private String sku;
    private Integer goodsNum;

    public InRepertoryDetailDTO(String sku, Integer goodsNum) {
        this.sku = sku;
        this.goodsNum = goodsNum;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
}
