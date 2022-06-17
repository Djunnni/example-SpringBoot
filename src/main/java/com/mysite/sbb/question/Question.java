package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 엔티티 속성은 @Column을 넣지 않아도 테이블 컬럼으로 인식한다.
    // 테이블 컬럼으로 인식하기 싫으면 @Transient를 넣으면 된다.
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 테이블의 실제 칼럼명은 create_date(스네이크 케이스)로 된다.
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;
}
