package cn.com.connext.oms.commons.dto;


import cn.com.connext.oms.commons.utils.ToMap;
import lombok.Data;

/**
 * @author xiamingxing
 * @date 2019/1/10 11:58
 */
@Data
public class OutRepoOrderDetailDto extends ToMap {
    private String goodsCode;
    private Integer num;

}
