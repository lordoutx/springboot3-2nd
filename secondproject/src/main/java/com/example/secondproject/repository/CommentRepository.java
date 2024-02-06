package com.example.secondproject.repository;

import com.example.secondproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특장게시글의 모든 댓글 조회 (@Query 어노테이션 사용)
    @Query(value = "SELECT * " +
                    "FROM TCOMMENT " +
                    "WHERE article_id = :articleId",
                    nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    //특정닉네임의 모든 댓글 조회 (네이티브 쿼리 META-INF>orm.xml 사용)
    List<Comment> findByNickname(String nickname);
}
