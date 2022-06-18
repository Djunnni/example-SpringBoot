package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    /*
    question 속성은 답변 엔티티에서 질문 엔티티를 참조하기 위해 추가했다.
    예를 들어 답변 객체(예:answer)를 통해 질문 객체의 제목을 알고 싶다면 answer.getQuestion().getSubject()처럼 접근할 수 있다.
    하지만 이렇게 속성만 추가하면 안되고 질문 엔티티와 연결된 속성이라는 것을 명시적으로 표시해야 한다.
     */
    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

    // ManyToMany로 관리할 경우에는 새로운 테이블들이 생성된다.
    // 다대다(N:N) 관계로 가기위해 서로 연관된 엔티티의 고유번호(id)가 들어간다.
    @ManyToMany
    Set<SiteUser> voter;
}
