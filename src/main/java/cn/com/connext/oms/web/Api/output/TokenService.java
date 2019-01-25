package cn.com.connext.oms.web.Api.output;

import cn.com.connext.oms.web.Api.API;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    public String getToken() {
        Map map = new HashMap();
        map.put("omsname", "123456");
        map.put("password","123456");
        String token=new RestTemplate().postForObject(API.GET_TOKEN_URL, map, String.class);
        return token;
    }
}
