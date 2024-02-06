package com.example.secondproject.entity;

import com.example.secondproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TCOMMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
    @SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQ", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        //예외발생
        if (dto.getId()!=null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        /*
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글 id가 잘못되었습니다. " +
                    dto.getArticleId() + " != " +
                    article.getId()
            );
         */

        //엔티티생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 댓글 id가 잘못되었습니다. " +
                    this.id + " != " +
                    dto.getId()
            );
        //엔티티 수정 및 반환
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
