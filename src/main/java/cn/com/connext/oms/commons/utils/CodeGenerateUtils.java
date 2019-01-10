package cn.com.connext.oms.commons.utils;

import java.util.UUID;

/**
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 14:46
 **/


public class CodeGenerateUtils {

    public static String creatUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        String str = CodeGenerateUtils.creatUUID();
        System.out.println(str);
    }
}

