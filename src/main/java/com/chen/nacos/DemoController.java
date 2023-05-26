package com.chen.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzhiying
 * @date 2023-05-15
 **/
@Slf4j
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @NacosValue(value = "${version}", autoRefreshed = true)
    private String version;

    /**
     * 测试类
     *
     * @return String
     * @author chenzhiying
     * @date 2023-05-15
     **/
    @GetMapping("/test")
    private String test() {
        log.info("版本号:{}", version);
        return version;
    }
}
