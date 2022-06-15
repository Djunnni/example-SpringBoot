package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	/*
	@Autowired
	객체를 주입하기 위해 사용하는 스프링의 애너테이션이다.
	객체를 주입하는 방식에는 @Autowired 외에 Setter 또는 생성자를 사용하는 방식이 있다.
	순환참조 문제와 같은 이유로 @Autowired 보다는 생성자를 통한 객체 주입방식이 권장된다.
	하지만 테스트 코드의 경우에는 생성자를 통한 객체의 주입이 불가능하므로
	테스트 코드 작성시에만 @Autowired를 사용하고 실제 코드 작성시에는 생성자를 통한 객체 주입방식을 사용하겠다.
	 */
	@Autowired
	private QuestionRepository questionRepository;

//	@Test
//	void testJpa() {
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?");
//		q1.setContent("sbb에 대해서 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//
//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);  // 두번째 질문 저장
//	}
//
//	@Test
//	void testQuestion() {
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());
//	}
//
//	@Test
//	void testFindOne() {
//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("sbb가 무엇인가요?", q.getSubject());
//		}
//	}
//	@Test
//	void testBySubject() {
//		Question oq = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(1, oq.getId());
//	}
	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}


}
