package cn.com.connext.oms.commons.dto.exchange.OMS;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/10
 * @time: 10:54
 * @describe: 退换货详情
 **/
public class GoodDetails {
    private String goodsSku;
    private Integer goodsNum;

    public GoodDetails() {
    }

    public GoodDetails(String goodsSku, Integer goodsNum) {
        this.goodsSku = goodsSku;
        this.goodsNum = goodsNum;
    }

    public String getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(String goodsSku) {
        this.goodsSku = goodsSku;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
}
