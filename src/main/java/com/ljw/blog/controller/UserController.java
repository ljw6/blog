package com.ljw.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljw.blog.dao.UserDao;
import com.ljw.blog.entity.Users;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@RestController
@RequestMapping("/test")
@MapperScan("com.ljw.blog.dao")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("hello")
    public String Sayhello() {
        return "Hello";
    }

    @GetMapping("index")
    public String index() {
        return "hello index";
    }

    @GetMapping("checkUserOnly")
    public Boolean checkOnly(@RequestParam String username) throws UnsupportedEncodingException {
        username = URLDecoder.decode(username, "UTF-8");
        System.out.println(username);
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        try {
            Users users = userDao.selectOne(wrapper);
            System.out.println(users);
            return users == null;
        } catch (TooManyResultsException e) {
            System.out.println("超过1条数据");
            return false;
        }
    }

    @GetMapping("checkPhoneOnly")
    public Boolean checkPhone(@RequestParam int phone) {
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("phone", phone);
        Users users = userDao.selectOne(wrapper);
        System.out.println(users);
        return users == null;
    }


    @GetMapping("registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Users users = new Users();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

//    @PostMapping(value = "registration", produces = "application/json")
//    public String createNewUser(@RequestBody Users users){
//        userDao.insert(users);
//        return "注册成功";
//    }

    @PostMapping(value = "registration", produces = "application/json")
    @ResponseBody
    public String createNewUser(@RequestBody Users users) {
        userDao.insert(users);
        return "注册成功";
    }
//    /*
//    基于角色的方法控制
//     */
//    @GetMapping("up")
//    @Secured("ROLE_sale")
//    public String up(){
//        return "hello up";
//    }
}
