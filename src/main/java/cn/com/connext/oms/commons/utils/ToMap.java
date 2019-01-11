package cn.com.connext.oms.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author xiamingxing
 * @date 2019/1/10 12:00
 */
public class ToMap {
    public MultiValueMap<String, Object> toMap() {
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<>();
        ObjectMapper oMapper = new ObjectMapper();
        Map map = oMapper.convertValue(this, Map.class);
        for (Object key : map.keySet()) {
            result.add((String) key, map.get(key));
        }
        return result;

    }
}
