package com.ljw.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("article")
public class Article {
    private int id;
    private String content;
    private Date date;
}
