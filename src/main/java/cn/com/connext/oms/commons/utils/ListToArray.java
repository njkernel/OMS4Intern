package cn.com.connext.oms.commons.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
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

    /**
     * create by: yonyong
     * description: 数组格式化工具用于过滤数量为0的商品
     * create time: 2019/1/21 22:13
     *
     *  * @Param: test
     * @return int[]
     */
    public static int[] ListFormat(int []test){
        return ArrayUtils.removeAllOccurences(test,0);
    }

    public static void main(String[] args) {
        int a[]={0,1};
        a=ListToArray.ListFormat(a);
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
}
