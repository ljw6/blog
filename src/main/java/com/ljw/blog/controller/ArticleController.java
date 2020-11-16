package com.ljw.blog.controller;

import com.ljw.blog.entity.Article;
import com.ljw.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@RestController
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/article/{id}")
//    public String getRes(HttpServletRequest request, @PathVariable int id){
//        Article article = articleService.findArticle(id);
////        request.setAttribute("article",article);//设置属性
////        return "Article";//返回的是templates中html文件的名称前缀
//        return article.getContent();
//    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public String getA(@PathVariable int id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Article article = articleService.findArticle(id);
        return article.getContent();
    }

}
