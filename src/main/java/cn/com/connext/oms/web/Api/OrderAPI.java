package cn.com.connext.oms.web.Api;

import cn.com.connext.oms.commons.utils.HttpClientUtils.HttpClientUtil;
import cn.com.connext.oms.commons.utils.HttpClientUtils.common.HttpConfig;
import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.commons.utils.MapperUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: OrderAPI</p>
 * <p>Description: </p>
 * 接口调用测试
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/6 10:53
 */
public class OrderAPI {

    public static String post() throws HttpProcessException {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("phone","13024535822");
        map.put("password","123456");
        String s = HttpClientUtil.post(HttpConfig.custom().client(),
                "http://localhost:8502/login",
                HttpConfig.custom().headers(),
                map, HttpConfig.custom().context(),
                HttpConfig.custom().encoding());
        return s;
    }

    /**
     * 测试调用订单接口
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getAllOrder() throws Exception {
        String order = HttpClientUtil.get(HttpConfig.custom().url("http://localhost:8502/getAllOrder"));
        Map<String, Object> map = MapperUtils.json2map(order);
        return map;
    }
}
