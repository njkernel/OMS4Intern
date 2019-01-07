package cn.com.connext.oms.service;

/**
 * <p>Title: TbAbnormalService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/7 10:58
 */
public interface TbAbnormalService {
    /**
     * 订单预检
     * @param id
     * @return
     */
    String checkGoods(int id);
}
