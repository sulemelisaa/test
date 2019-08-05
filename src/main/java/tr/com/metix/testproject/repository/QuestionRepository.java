package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Question;

import java.util.Optional;

public interface QuestionRepository  extends JpaRepository<Question,Long> {

    Optional<Question> findById(Long id); //test id si bu olan Question satırını getir

}
