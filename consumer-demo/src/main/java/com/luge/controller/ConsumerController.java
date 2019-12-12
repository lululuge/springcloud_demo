package com.luge.controller;

import com.luge.domain.User;
//import com.luge.feign.UserClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import javafx.beans.DefaultProperty;
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
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
////    private UserClient userClient;
////
////    /**
////     * 利用feign实现远程调用
////     */
////    @RequestMapping(path = "/findOne2")
////    public User findOne2(@RequestParam("id") Integer id) {
////        return userClient.findOne2(id);
////    }

//    @Autowired
//    private DiscoveryClient discoveryClient;

//    @Autowired
//    private RibbonLoadBalancerClient client;

    @RequestMapping(path = "/findOne")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    @HystrixCommand
    public String findOne(@RequestParam("id") Integer id) {
        String url = "http://user-service/user/findOne?id=" + id;
        String user = restTemplate.getForObject(url, String.class);
        System.out.println(user);
        return user;
//        // 方式3
//        String url = "http://user-service/user/findOne?id=" + id;
//        User user = this.restTemplate.getForObject(url, User.class);
//        System.out.println(user);
//        return user;
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

    }

    public String defaultFallback() {
        return "当前访问人数过多，请稍后再试！";
    }
}
