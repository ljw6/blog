package com.ljw.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljw.blog.dao.UserDao;
import com.ljw.blog.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");//配置权限角色

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        Users user = userDao.selectOne(wrapper);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), auth);
    }
}
