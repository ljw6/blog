package com.ljw.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljw.blog.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<Users> {
}
