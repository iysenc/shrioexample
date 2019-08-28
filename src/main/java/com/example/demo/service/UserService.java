package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by demo on 2019/8/3 - 9:21
 * version:1.0.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 调用userDao.findUserById得到User
     *
     * @param username
     * @return
     */
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
