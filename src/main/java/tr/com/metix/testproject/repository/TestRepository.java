package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.domain.Test;

public interface TestRepository  extends JpaRepository<Test,Long> {


}
