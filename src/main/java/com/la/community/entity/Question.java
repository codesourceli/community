package com.la.community.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

//@Alias("Question")
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private Long creatTime;
    private Long modifyTime;
    private Integer creatorId;
    private int commentCount;
    private int likeCount;
    private String tag;

}
