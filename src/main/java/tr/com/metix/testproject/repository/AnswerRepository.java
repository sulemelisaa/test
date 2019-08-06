package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Answer;

public interface AnswerRepository  extends JpaRepository<Answer,Long> {



}
