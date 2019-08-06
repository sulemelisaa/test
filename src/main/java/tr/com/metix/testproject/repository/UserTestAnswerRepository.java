package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.UserTestAnswer;

public interface UserTestAnswerRepository extends JpaRepository<UserTestAnswer,Long> {
}
