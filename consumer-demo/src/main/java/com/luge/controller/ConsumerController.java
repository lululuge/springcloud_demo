package com.luge.controller;

import com.luge.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping(path = "/consumer")
@ResponseBody
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

//    @Autowired
//    private RibbonLoadBalancerClient client;

    @RequestMapping(path = "/findOne")
    public User findOne(@RequestParam("id") Integer id) {
//        // 传统方法获取url
//        String url = "http://localhost:8080/user/findOne?id=" + id;
//        System.out.println(url);
        // 方式1：利用eureka动态获取url
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        ServiceInstance instance = instances.get(0);
//        // 方式2：利用ribbon获取实例
//        ServiceInstance instance = client.choose("user-service");
//        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/findOne?id=" + id;
//        System.out.println(url);
        String url = "http://user-service/user/findOne?id=" + id;
        User user = this.restTemplate.getForObject(url, User.class);
        System.out.println(user);
        return user;
    }
}
