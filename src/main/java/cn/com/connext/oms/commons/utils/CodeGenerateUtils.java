package cn.com.connext.oms.commons.utils;
 
import java.util.Random;
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

	/**
	 * create by: yonyong
	 * description: 修改UUID工具类，弃用uuid生成方法，使用新的id生成方法用于生成单号
	 * create time: 2019/1/21 12:14
	 *
	 *  * @Param:
	 * @return java.lang.String
	 */
	public static String creatUUID() {

		return String.valueOf(genItemId());
	}
 
	public static void main(String[] args) {
		String str = CodeGenerateUtils.creatUUID();
		System.out.println(str);
		System.out.println(String.valueOf(genItemId()));
	}

	public static long genItemId() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
}
