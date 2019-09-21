package com.la.community.dto;

import com.la.community.entity.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Long creatTime;
    private Long modifyTime;
    private Integer creatorId;
    private int commentCount;
    private int likeCount;
    private String tag;
    private User user;

}
