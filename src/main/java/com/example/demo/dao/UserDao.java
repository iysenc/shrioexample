package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by demo on 2019/8/3 - 9:18
 * version:1.0.0
 */
@Repository
@Mapper
@CacheConfig(cacheNames = "user")
public interface UserDao {
    /**
     * 根据username查找用户
     *
     * @return
     */
    @Cacheable(key = "#p0")
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);
}
