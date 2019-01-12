package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.service.MyTest;
import cn.com.connext.oms.web.Api.OutputApi;
import org.springframework.stereotype.Service;

/**
 * @program: OMS4Intern
 * @description: 123
 * @author: Lili.Chen
 * @create: 2019-01-09 13:10
 **/
@Service
public class MyTestImpl implements MyTest {
    @Override
    public String a() throws HttpProcessException {
        String b= OutputApi.post();

        return b;
    }
}
