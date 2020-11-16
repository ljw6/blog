package com.ljw.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class Users {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String username;
    private int phone;
    private String password;
}
