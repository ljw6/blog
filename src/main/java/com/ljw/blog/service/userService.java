package com.ljw.blog.service;

import com.ljw.blog.entity.Users;

public interface userService {
    public Users findUserByName(String name);

    public void saveUser(Users users);
}
