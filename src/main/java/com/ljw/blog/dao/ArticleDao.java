package com.ljw.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljw.blog.entity.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends BaseMapper<Article> {
}
