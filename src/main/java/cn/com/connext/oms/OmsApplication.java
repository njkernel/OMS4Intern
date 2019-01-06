package cn.com.connext.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
@EnableTransactionManagement
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "cn.com.connext.oms")
@MapperScan(basePackages = "cn.com.connext.oms.mapper")
public class OmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class, args);
    }

}

