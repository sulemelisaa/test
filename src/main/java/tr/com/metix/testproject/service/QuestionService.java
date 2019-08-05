package tr.com.metix.testproject.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.QuestionRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.QuestionDTO;

import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    private final UserService userService;
    private final QuestionRepository questionRepository;

    public QuestionService(UserService userService, QuestionRepository questionRepository) {
        this.userService = userService;
        this.questionRepository = questionRepository;
    }
/*
    public Question createQuestion (QuestionDTO questionDTO) {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Question question = new Question();
        Optional<Question> q = questionRepository.findById(question.getId());

        question.setValue(questionDTO.getValue());
        question.setTest();

        customerRepository.save(customer);
        return customer;
    }

 */
}
