package com.la.community.service.impl;

import com.la.community.entity.User;
import com.la.community.mapper.IUserMapper;
import com.la.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    /**
     * 第一次登录社区将用户插入数据插入数据库
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {
        int result = userMapper.insert(user);
        return result;
    }

    /**
     * 验证数据库中是否有该用户是否第一次登录该社区
     * @param id
     * @return
     */
    @Override
    public User findUserByGithubId(String id) {
        User user = new User();
        user.setAccountId(id);
        user=userMapper.selectOne(user);
        return user;
    }
}
