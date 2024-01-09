package com.example.secondproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    //브라우저에서 localhost:8080/hi URL로 요청하면 메소드 실행
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        //컨트롤러에서 모델로 변수를 넘기기 위해 model 정의
        //모델에서 사용할 변수 등록, 등록된 변수는 뷰 페이지로 넘어감
        model.addAttribute("username","홍팍");

        //resources>template>greetings.mustache 파일 반환
        return "greetings";
    }

    //브라우저에서 localhost:8080/bye URL로 요청하면 메소드 실행
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        //컨트롤러에서 모델로 변수를 넘기기 위해 model 정의
        //모델에서 사용할 변수 등록, 등록된 변수는 웹브라우저(뷰 페이지)로 넘어감
        model.addAttribute("username","홍길동");

        //resources>template>greetings.mustache 파일 반환
        return "goodbye";
    }
}
