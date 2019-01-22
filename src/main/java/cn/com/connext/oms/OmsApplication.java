package cn.com.connext.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
//事务注解
@EnableTransactionManagement
//接口文档
@EnableSwagger2
//入口扫描
@SpringBootApplication(scanBasePackages = "cn.com.connext.oms")
//mybatis扫描路径
@MapperScan(basePackages = "cn.com.connext.oms.mapper")
public class OmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class, args);
    }
}

