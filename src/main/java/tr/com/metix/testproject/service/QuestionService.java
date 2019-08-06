package tr.com.metix.testproject.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.QuestionRepository;
import tr.com.metix.testproject.repository.TestRepository;
import tr.com.metix.testproject.repository.UserRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.QuestionDTO;
import tr.com.metix.testproject.service.mapper.QuestionMapper;
import tr.com.metix.testproject.service.mapper.TestMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {

    private final UserService userService;
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UserRepository userRepository;
    private  final TestMapper testMapper;
    private final TestRepository testRepository;

    public QuestionService(UserService userService, QuestionRepository questionRepository, QuestionMapper questionMapper, UserRepository userRepository, TestMapper testMapper, TestRepository testRepository) {
        this.userService = userService;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.userRepository = userRepository;
        this.testMapper = testMapper;
        this.testRepository = testRepository;
    }

    public List<QuestionDTO> getQuestion(){
        List<QuestionDTO>  question = questionRepository.findAll().stream().map(questionMapper::questionToQuestionDTO).collect(Collectors.toCollection(LinkedList::new));
        return question;
    }


    public void deleteQuestion(Long id) throws BadRequestAlertException {
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
            throw new BadRequestAlertException("Yalnızca Admin Rolü Soruyu silebilir!! ", null, "test");
        }

        questionRepository.deleteById(id);

    }

    public QuestionDTO createQuestion (QuestionDTO questionDTO) throws BadRequestAlertException {

        // Current User
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        System.out.print("curret yetkisiiii: " +u.get().getAuthorities());
        //current'ın user tablosundaki satırı
        Optional<User> user = userRepository.findById(u.get().getId());
        System.out.print("user yetkisiiii: " + user.get().getAuthorities());

    //    Question question = new Question();

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
            //          System.out.print("\n hatalı: " + user.get().getAuthorities());
            //           System.out.print("\n hatalı id: " + u.get().getId() + "\n ");
            throw new BadRequestAlertException("Yalnızca Admin Rolü Soru ekleyebilir!! ", null, "test");
        }
        
        Question question = questionMapper.questionDTOToQuestion(questionDTO);
        question = questionRepository.save(question);
        return questionMapper.questionToQuestionDTO(question);


    }




}
