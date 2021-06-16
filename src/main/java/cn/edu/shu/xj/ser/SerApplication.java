package cn.edu.shu.xj.ser;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

@MapperScan("cn.edu.shu.xj.ser.*")
@SpringBootApplication
public class SerApplication {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        //可以通过环境变量获取你的mapper路径,这样mapper扫描可以通过配置文件配置了
        scannerConfigurer.setBasePackage("cn.edu.shu.xj.ser.mapper.*");
        return scannerConfigurer;
    }
    public static void main(String[] args) {
        SpringApplication.run(SerApplication.class, args);
    }

}
