package cn.com.connext.oms.web.Api;

import cn.com.connext.oms.commons.utils.HttpClientUtils.HttpClientUtil;
import cn.com.connext.oms.commons.utils.HttpClientUtils.common.HttpConfig;
import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: OMS4Intern
 * @description: 调用WMS有关出库单的接口
 * @author: Lili.Chen
 * @create: 2019-01-09 12:48
 **/
public class OutputAPI {

    /**
    * @Description: 从wms获取出库单的状态（用出库单编码）
    * @Param: [outputCode]
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2019/1/9
    */
    public static String getOutPutState(String outputCode) throws HttpProcessException {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("outRepoOrderNo",outputCode);
        String s = HttpClientUtil.post(HttpConfig.custom().client(),
                API.WMS_HOST+"/api/getOutRepoOrderStatus",
                HttpConfig.custom().headers(),
                map, HttpConfig.custom().context(),
                HttpConfig.custom().encoding());
        return s;
    }



    public static String updateOutputStateOfWMS(String outputCodeArray) throws HttpProcessException {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("outRepoOrderNo",outputCodeArray);
        String s = HttpClientUtil.post(HttpConfig.custom().client(),
                API.WMS_HOST+"/api/cancelResult",
                HttpConfig.custom().headers(),
                map, HttpConfig.custom().context(),
                HttpConfig.custom().encoding());
        return s;
    }



    public static String post() throws HttpProcessException {
        Map<String,Object> map=new HashMap<String, Object>();
        String[] a=new String[2];
        a[0]=1+"";
        a[1]=2+"";
      /*  String a="1,2";*/
        for(String s:a){
            System.out.println(s);
        }

        map.put("outRepoOrderIdArray",a);
        String s = HttpClientUtil.post(HttpConfig.custom().client(),
                API.WMS_HOST+"/api/cancelResult",
                HttpConfig.custom().headers(),
                map, HttpConfig.custom().context(),
                HttpConfig.custom().encoding());
        return s;
    }
}
