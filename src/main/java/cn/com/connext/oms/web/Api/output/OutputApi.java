package cn.com.connext.oms.web.Api.output;

import cn.com.connext.oms.commons.dto.output.OutRepoOrderDetailDto;
import cn.com.connext.oms.commons.dto.output.OutputDTO;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.web.Api.API;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


/**
 * <p>Title: OutputApi</p>
 * <p>Description: 出库模块，推送出库单 </p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/7
 */
public class OutputApi {
    public static String post(TbOutput tbOutput, TbOrder tbOrder, TbReceiver tbReceiver, List<OutRepoOrderDetailDto> repoOrderDetailDto) throws Exception {
        OutputDTO outputDTO = new OutputDTO(
                tbOutput.getOutputCode(),
                tbOutput.getOrderId(),
                tbOrder.getChannelCode(),
                tbReceiver.getReceiverName(),
                tbReceiver.getReceiverAddress(),
                tbOrder.getDeliveryCompany(),
                repoOrderDetailDto
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("token",new TokenService().getToken());
        HttpEntity<Map> httpEntity = new HttpEntity<Map>(outputDTO.toMap(),httpHeaders);
        String result = new RestTemplate().postForObject(API.API_OUTPUT, httpEntity, String.class);
        return result;
    }
}
//    /**
//     * 调用WMS接口，获取返回值
//     * @return
//     * @throws Exception
//     */
//    public static int isReceived() throws Exception {
//        int t = HttpClientUtil.get(HttpConfig.custom().url("http://localhost:8502/"));
//        return t;
//    }

