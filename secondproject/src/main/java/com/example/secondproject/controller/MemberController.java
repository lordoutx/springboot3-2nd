package com.example.secondproject.controller;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.dto.MemberForm;
import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Member;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/members/new")
    public String newArticleForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form){
        System.out.println(form.toString());
        //DTO -> Entity로 변환
        Member member = form.toEntity();
        System.out.println(member.toString());
        //Repository를 사용하여 Entity를 DB에 저장
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "members/new";
    }
}
