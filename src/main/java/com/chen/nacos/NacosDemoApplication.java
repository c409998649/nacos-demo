package com.chen.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDemo(globalProperties = @DemoProperties(serverAddr = "${nacos.config.server-addr}", namespace = "${nacos.config.namespace}", enableRemoteSyncConfig = "true", maxRetry = "5", configRetryTime = "4000", configLongPollTimeout = "26000", username = "${nacos.config.username}", password = "${nacos.config.password}"))
public class NacosDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDemoApplication.class, args);
    }
}
