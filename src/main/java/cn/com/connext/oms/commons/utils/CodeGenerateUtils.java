package cn.com.connext.oms.commons.utils;
 
import java.util.UUID;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 01:51
 * @describe:
 **/
public class CodeGenerateUtils {
 	public static int ID=1901060005;
	public static String creatUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static int creatId(){
		return ID+1;
	}
	public static void main(String[] args) {
		String str = CodeGenerateUtils.creatUUID();
		System.out.println(str);
	}
}
