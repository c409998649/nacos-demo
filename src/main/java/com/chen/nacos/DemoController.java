package com.chen.nacos;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author chenzhiying
 * @date 2023-05-15
 **/
@Slf4j
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @Qualifier("globalNacosProperties")
    private final Properties globalNacosProperties;

    /**
     *
     *
     * @return String
     * @author chenzhiying
     * @date 2023-05-15
     **/
    @GetMapping("/test")
    private Properties test() {
        log.info("parameter:{}", globalNacosProperties);
        return globalNacosProperties;
    }
}
