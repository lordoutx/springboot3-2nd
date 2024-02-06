package com.example.secondproject.repository;

import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정게시글의 모든 댓글 조회")
    void findByArticleId() {
        //1.입력데이터 준비
        Long articleId = 141L;
        System.out.println("===>>1111");
        //2.실제데이터
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        System.out.println("===>>2222");
        //3.예상데이터
        Article article = new Article(141L,"당신의 인생 영화는?","댓글 고");
        Comment a = new Comment(1L,article,"Park","굿 윌 헌팅");
        Comment b = new Comment(2L,article,"Kim","아이 엠 샘");
        Comment c = new Comment(3L,article,"Choi","쇼생크 탈출");
        List<Comment> expected = Arrays.asList(a,b,c);
        System.out.println("===>>3333");

        //4.비교 검증
        assertEquals(expected.toString(),comments.toString(),"141번 글의 모든 댓글 출력!");

    }

    @Test
    void findByNickname() {
        //1.입력데이터 준비
        //2.실제데이터
        //3.예상데이터
        //4.비교 검증
    }
}