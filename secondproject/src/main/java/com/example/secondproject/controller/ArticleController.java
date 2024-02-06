package com.example.secondproject.controller;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.dto.CommentDto;
import com.example.secondproject.entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 Data를 DB에서 불러와 뷰페이지에 보여주기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        //뷰페이지에서 수정된 데이터를 읽어와 DB에 반영(Update)하기
        log.info(form.toString());

        //DTO -> Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //Repository를 사용하여 Entity를 DB에 저장
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target !=null){
            articleRepository.save(articleEntity);
        }
        log.info(articleEntity.toString());
        return "redirect:/articles/" + articleEntity.getId();
    }

    //update, delete는 @Postmapping patch, delete로 처리할 수 있으나
    //HTML에서는 GET, POST만 지원함. javascript에서는 PATCH,DELETE 사용 가능함
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상 엔티티 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.!!");
        }

        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //url -> controller -> repository-DB -> entity -> DTO -> view page
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id)
                .orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        //2. 모델에 데이터 등록하기
        //model에 등록한 article 변수는 view page에서 {{#article}} {{/article}} 블록으로 사용
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);

        //3. 뷰페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. DB에서 모든 Article 데이터 가져오기
        //repository에서 findAll의 반환형을 Iterable타입 -> ArrayList타입으로 형변환한다.
        List<Article> articleEntityList = articleRepository.findAllByOrderById();

        //2. 가져온 Article을 모델에 등록하기
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰페이지 반환하기
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());

        //DTO -> Entity로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        //Repository를 사용하여 Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
}
