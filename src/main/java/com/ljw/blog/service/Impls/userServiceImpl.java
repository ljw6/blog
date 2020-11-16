package com.ljw.blog.service.Impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljw.blog.dao.UserDao;
import com.ljw.blog.entity.Users;
import com.ljw.blog.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class userServiceImpl implements userService {

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users findUserByName(String name) {
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("name", name);
        Users users = userDao.selectOne(wrapper);
        return users;
    }

    @Override
    public void saveUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userDao.insert(users);
    }
}
