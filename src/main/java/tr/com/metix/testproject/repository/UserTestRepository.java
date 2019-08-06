package tr.com.metix.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.metix.testproject.domain.UserTest;

public interface UserTestRepository extends JpaRepository<UserTest,Long> {
}
