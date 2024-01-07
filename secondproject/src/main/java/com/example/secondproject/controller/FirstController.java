package com.example.secondproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")        //브라우저 URL에서 localhost:8080/hi를 입력하면 아래 메소드의 return을 반환
    public String niceToMeetYou(Model model){       //컨트롤러에서 모델로 변수를 넘기기 위해 model 정의
        //모델에서 사용할 변수 등록, 등록된 변수는 웹브라우저(뷰 페이지)로 넘어감
        model.addAttribute("username","홍팍");
        return "greetings";     //resources>template>greetings.mustache 파일 반환
    }
}
