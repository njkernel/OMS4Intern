package cn.com.connext.oms.web.Api;

import cn.com.connext.oms.commons.dto.OutRepoOrderDetailDto;
import cn.com.connext.oms.commons.dto.OutputDTO;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.entity.TbReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.List;


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
        OutputDTO outputDTO=new OutputDTO(
                tbOutput.getOutputCode(),
                tbOutput.getOrderId(),
                tbOrder.getChannelCode(),
                tbReceiver.getReceiverName(),
                tbReceiver.getReceiverAddress(),
                tbOrder.getDeliveryCompany(),
                repoOrderDetailDto
        );
        String result= new RestTemplate().postForObject("http://10.129.100.131:8080/api/pushOutRepoOrder",outputDTO.toMap(),String.class);
        return result;
    }
    }
//        Map<String,Object> map=new HashMap<String, Object>();
//        // 存入所需要的字段和数据
//        map.put("outRepoId",tbOutput.getOutputCode());
//        map.put("orderId",tbOutput.getOrderId());
//        map.put("channelId",tbOrder.getChannelCode());
//        map.put("receiverName",tbReceiver.getReceiverName());
//        map.put("receiverAddress",tbReceiver.getReceiverAddress());
//        map.put("expressCompany",tbOrder.getDeliveryCompany());
//        map.put("outRepoOrderDetailDto",);
//        // 传送是否成功接收返回值
//        String s = HttpClientUtil.post(HttpConfig.custom().client(),
//                 // 连接到WMS的地址er
//                "http://10.129.100.131:8080/api/pushOutRepoOrder",
//                HttpConfig.custom().headers(),
//                map, HttpConfig.custom().context(),
//                HttpConfig.custom().encoding());
//        return s;
//   }
//
//    /**
//     * 调用WMS接口，获取返回值
//     * @return
//     * @throws Exception
//     */
//    public static int isReceived() throws Exception {
//        int t = HttpClientUtil.get(HttpConfig.custom().url("http://localhost:8502/"));
//        return t;
//    }

