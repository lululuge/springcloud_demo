package com.luge.controller;

import com.luge.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path = "/consumer")
@ResponseBody
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/findOne")
    public User findOne(@RequestParam("id") Integer id) {
        String url = "http://localhost:8080/user/findOne?id=" + id;
        System.out.println(url);
        User user = this.restTemplate.getForObject(url, User.class);
        System.out.println(user);
        return user;
    }
}
