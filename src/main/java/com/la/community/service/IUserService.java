package com.la.community.service;

import com.la.community.entity.User;

public interface IUserService {
    int insert(User user);

    User findUserByGithubId(String id);
}
