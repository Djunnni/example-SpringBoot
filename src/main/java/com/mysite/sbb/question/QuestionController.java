package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    /*
        우선 @RequiredArgsConstructor 애너테이션으로 questionRepository 속성을 포함하는 생성자를 생성하였다.
        @RequiredArgsConstructor는 롬복이 제공하는 애너테이션으로 final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다.
        롬복의 @Getter, @Setter가 자동으로 Getter, Setter 메서드를 생성하는 것과 마찬가지로 @RequiredArgsConstructor는 자동으로 생성자를 생성한다.
        따라서 스프링 의존성 주입 규칙에 의해 questionRepository 객체가 자동으로 주입된다.
     */
    /*
        스프링의 의존성 주입(Dependency Injection) 방식 3가지
        @Autowired 속성 - 속성에 @Autowired 애너테이션을 적용하여 객체를 주입하는 방식
        생성자 - 생성자를 작성하여 객체를 주입하는 방식 (권장하는 방식)
        Setter - Setter 메서드를 작성하여 객체를 주입하는 방식 (메서드에 @Autowired 애너테이션 적용이 필요하다.)
     */
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
}
