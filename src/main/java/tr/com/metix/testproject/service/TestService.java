package tr.com.metix.testproject.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.UserRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.TestDTO;

import java.util.Optional;

@Service
@Transactional
public class TestService {
    private final UserService userService;
    private final UserRepository userRepository;

    public TestService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Long createTest(TestDTO testDTO) {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());

        Optional<User> user = userRepository.findById(u.get().getId()); //current ın user tablosundaki satırı

        if (user.get().getAuthorities().contains("ROLE_ADMIN")) {
            System.out.println("Role admin : " + u.get().getId() + user.get().getAuthorities());
        }

        else {
            System.out.println("user admin : " + u.get().getId()+ user.get().getAuthorities());
        }
        return u.get().getId();

        /*
        if(u)


        Question question = new Question();
        Optional<Question> q = questionRepository.findById(question.getId());

        question.setValue(questionDTO.getValue());
        question.setTest();

        customerRepository.save(customer);
        return customer;

      */
    }
}


