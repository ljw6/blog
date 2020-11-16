package com.ljw.blog.service.Impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljw.blog.dao.ArticleDao;
import com.ljw.blog.entity.Article;
import com.ljw.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article findArticle(int id) {
        Article article = articleDao.selectById(id);
        return article;
    }
}
