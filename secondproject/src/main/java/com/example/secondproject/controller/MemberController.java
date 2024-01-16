package com.example.secondproject.controller;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.dto.MemberForm;
import com.example.secondproject.entity.Article;
import com.example.secondproject.entity.Member;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 Data를 DB에서 불러와 뷰페이지에 보여주기
        log.info("member id ===> " + id);
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        //뷰페이지에서 수정된 데이터를 읽어와 DB에 반영(Update)하기
        log.info(form.toString());

        //DTO -> Entity로 변환
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());

        //Repository를 사용하여 Entity를 DB에 저장
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target !=null){
            memberRepository.save(memberEntity);
        }
        log.info(memberEntity.toString());
        return "redirect:/members/" + memberEntity.getId();
    }

    //update, delete는 @Postmapping patch, delete로 처리할 수 있으나
    //HTML에서는 GET, POST만 지원함. javascript에서는 PATCH,DELETE 사용 가능함
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //1. 삭제할 대상 가져오기
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상 엔티티 삭제
        if (target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.!!");
        }

        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/members";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id ===> " + id);
        //1. id를 조회해 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록하기
        //model에 등록한 article 변수는 view page에서 {{#member}} {{/member}} 블록으로 사용
        model.addAttribute("member",memberEntity);

        //3. 뷰페이지 반환하기
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        //1. DB에서 모든 Member 데이터 가져오기
        //repository에서 findAll의 반환형을 Iterable타입 -> ArrayList타입으로 형변환한다.
        List<Member> memberEntityList = memberRepository.findAllByOrderById();
        log.info("== memberEntityList ==");


        //2. 가져온 Article을 모델에 등록하기
        model.addAttribute("memberList",memberEntityList);
        log.info("== memberList ==");

        //3. 뷰페이지 반환하기
        return "members/index";
    }

    @GetMapping("/members/new")
    public String newArticleForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form){
        log.info("val form ==> " + form.toString());

        //DTO -> Entity로 변환
        Member member = form.toEntity();
        log.info("val entity ==> " + member.toString());

        //Repository를 사용하여 Entity를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info("val table ==> " + saved.toString());
        return "redirect:/members/" + saved.getId();
    }
}
