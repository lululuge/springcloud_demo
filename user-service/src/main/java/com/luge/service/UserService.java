package com.luge.service;

import com.luge.dao.UserDao;
import com.luge.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findById(Integer id) {
        // 休眠2秒
//        try {
//            Thread.sleep(4000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        User user = userDao.selectByPrimaryKey(id);
        return user;
    }
}
