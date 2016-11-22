package com.company.web.controller;

import com.company.web.service.ComputeService;
import com.company.web.util.ApplicationSupport;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ComputeService computeService;

    /**
     * curl  http://localhost:9021/add
     *
     * @return
     */

    @RequestMapping(value = "/compute", method = RequestMethod.GET)
    public String add() {
        logger.info("{}", "srping-ribbon-service-add2 method");
//        Object iRule = ApplicationSupport.getBean("IRule");
//        Object beanByClass = ApplicationSupport.getBeanByClass(IRule.class);
//        System.out.println("iRule = " + beanByClass);

        RoundRobinRule ribbonRule = (RoundRobinRule) ApplicationSupport.getBeanByClass(RoundRobinRule.class);
        System.out.println("ribbonRule = " + ribbonRule);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://DIDISPACE/compute", String.class);
        return forEntity.getBody();
    }

    @RequestMapping(value = "/compute2", method = RequestMethod.GET)
    public String add2() {
        logger.info("{}", "srping-ribbon-service-add Hystrix service method");
        return computeService.addService();
    }

}