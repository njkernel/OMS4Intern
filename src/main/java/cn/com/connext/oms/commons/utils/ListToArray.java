package cn.com.connext.oms.commons.utils;

import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/9
 * @time: 17:30
 * @describe:
 **/
public class ListToArray {
    public static int[] listToArray(List<Integer> list){

        int[] t=new int[list.size()];

        for (int i=0;i<t.length;i++){
            t[i]=list.get(i);
        }
        return t;
    }
}
