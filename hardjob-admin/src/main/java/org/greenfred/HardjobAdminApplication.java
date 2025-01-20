package org.greenfred;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication(scanBasePackages = {"org.greenfred"})
// 扫描 Mapper
@MapperScan(basePackages = {"org.greenfred.mappers"})
@EnableTransactionManagement
@EnableScheduling
public class HardjobAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HardjobAdminApplication.class, args);
    }
}
