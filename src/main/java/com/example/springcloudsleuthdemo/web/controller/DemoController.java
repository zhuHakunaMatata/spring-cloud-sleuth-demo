package com.example.springcloudsleuthdemo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kyle on 2018/10/26.
 */
@RestController
public class DemoController {

    protected final Logger logger = LoggerFactory.getLogger(DemoController.class);


    private RestTemplate restTemplate;

    @Autowired
    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public String greeting(){
        String value = "hello world";
        logger.info("{} greeting() : {}",DemoController.class.getName(), value);
        return value;
    }

    /**
     * 完整的调用链路：
     * spring-cloud-sleuth
     * -> zuul
     * -> person-client
     * -> person-service
     *
     * @return
     */
    @GetMapping("/to/zuul/person-client/person/find/all")
    public Object toZuul(){
        logger.info(" --------- spring-cloud-sleuth#toZuul().----------");
        String url = "http://spring-cloud-zuul/person-client/person/find/all";
        return restTemplate.getForObject(url,Object.class);
    }

}
