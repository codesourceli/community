package com.la.community.entity;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long creatTime;
    private Long modifyTime;
    private String avatarUrl;

}
