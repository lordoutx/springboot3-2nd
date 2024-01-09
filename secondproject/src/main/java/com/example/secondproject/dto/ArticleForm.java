package com.example.secondproject.dto;

import com.example.secondproject.entity.Article;

public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        //id가 null 인것은 현시점에는 id값을 알수 없으며, 실제 DB에 저장될때 id값을 자동 채번함
        return new Article(null,title,content);
    }
}
