package cn.com.connext.oms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/10
 * @time: 23:35
 **/

@Table(name = "oms.tb_exchange_order_relations")
public class TbExchangeOrderRelations {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 旧订单号
     */
    @Column(name = "oldOrderId")
    private Integer oldOrderId;

    /**
     * 新订单号
     */
    @Column(name = "newOrderId")
    private Integer newOrderId;

    /**
     * 对应的换货单号
     */
    @Column(name = "exchange_id")
    public Integer exchangeId;

    public TbExchangeOrderRelations() {
    }

    public TbExchangeOrderRelations(Integer id, Integer oldOrderId, Integer newOrderId, Integer exchangeId) {
        this.id = id;
        this.oldOrderId = oldOrderId;
        this.newOrderId = newOrderId;
        this.exchangeId = exchangeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(Integer oldOrderId) {
        this.oldOrderId = oldOrderId;
    }

    public Integer getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(Integer newOrderId) {
        this.newOrderId = newOrderId;
    }

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }
}
