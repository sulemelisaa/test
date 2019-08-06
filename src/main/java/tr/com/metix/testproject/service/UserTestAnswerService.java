package tr.com.metix.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.domain.UserTest;
import tr.com.metix.testproject.domain.UserTestAnswer;
import tr.com.metix.testproject.repository.AnswerRepository;
import tr.com.metix.testproject.repository.UserRepository;
import tr.com.metix.testproject.repository.UserTestAnswerRepository;
import tr.com.metix.testproject.repository.UserTestRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.UserTestAnswerDTO;
import tr.com.metix.testproject.service.mapper.UserTestAnswerMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserTestAnswerService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserTestAnswerRepository userTestAnswerRepository;
    private final UserTestAnswerMapper userTestAnswerMapper;
    private  final AnswerRepository answerRepository;
    private  final UserTestRepository userTestRepository;

    int score = 0;

    public UserTestAnswerService(UserService userService, UserRepository userRepository, UserTestAnswerRepository userTestAnswerRepository, UserTestAnswerMapper userTestAnswerMapper, AnswerRepository answerRepository, UserTestRepository userTestRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userTestAnswerRepository = userTestAnswerRepository;
        this.userTestAnswerMapper = userTestAnswerMapper;
        this.answerRepository = answerRepository;
        this.userTestRepository = userTestRepository;
    }


    public List<UserTestAnswerDTO> getUserTestAnswer(){
        List<UserTestAnswerDTO>  userTestAnswer = userTestAnswerRepository.findAll().stream().map(userTestAnswerMapper::usertestanswerTousertestanswerDTO).collect(Collectors.toCollection(LinkedList::new));
        return userTestAnswer;
    }

    public void deleteUserTestAnswer(Long id) throws BadRequestAlertException {
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());


        userTestAnswerRepository.deleteById(id);

    }

    public UserTestAnswerDTO createUserTestAnswer (UserTestAnswerDTO userTestAnswerDTO) throws BadRequestAlertException {


        // Current User
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        System.out.print("curret yetisiiii: " +u.get().getAuthorities());
        //current'ın user tablosundaki satırı
        Optional<User> user = userRepository.findById(u.get().getId());
        System.out.print("user yetkii: " + user.get().getAuthorities());

        //    Question question = new Question();


        UserTestAnswer userTest = userTestAnswerMapper.usertestanswerDTOToUsertestanswer(userTestAnswerDTO);
        userTest = userTestAnswerRepository.save(userTest);
        userTestAnswerMapper.usertestanswerTousertestanswerDTO(userTest);
 //       Optional<UserTestAnswer> userTest1 = userTestAnswerRepository.findById(userTest.getUsertest().stream().collect(Collectors)))); // UserTestAnswer daki Answer ID=52 olan satırı


     //   user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()
      //  if(userTest.getUsertest())

        return userTestAnswerDTO;



    }
/*
    public void isCorrect (UserTestAnswerDTO userTestAnswerDTO){
        Optional<Answer> ans = answerRepository.findById(userTestAnswerDTO.getAnswerId());
        Optional<UserTest> ust = userTestRepository.findById(userTestAnswerDTO.getUserTestDTOS().contains());
        if (ans.get().isCorrect()){
            //
        }

    }

*/
}
