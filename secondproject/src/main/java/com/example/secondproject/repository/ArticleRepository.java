package com.example.secondproject.repository;

import com.example.secondproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();   //원래 Iterable 타입으로 반환하나 이를 ArrayList로 변환

    ArrayList<Article> findAllByOrderById();
}
