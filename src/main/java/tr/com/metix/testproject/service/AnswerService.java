package tr.com.metix.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.domain.UserTest;
import tr.com.metix.testproject.repository.AnswerRepository;
import tr.com.metix.testproject.repository.UserRepository;
import tr.com.metix.testproject.repository.UserTestRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.AnswerDTO;
import tr.com.metix.testproject.service.dto.UserTestAnswerDTO;
import tr.com.metix.testproject.service.mapper.AnswerMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService {

    private final UserService userService;
    private final UserRepository userRepository;
    private  final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private  final UserTestRepository userTestRepository;


    public AnswerService(UserService userService, UserRepository userRepository, AnswerRepository answerRepository, AnswerMapper answerMapper, UserTestRepository userTestRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.userTestRepository = userTestRepository;
    }

    public void deleteAnswer(Long id) throws BadRequestAlertException {
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
            throw new BadRequestAlertException("Yalnızca Admin Rolü cevap silebilir!! ", null, "test");
        }
        answerRepository.deleteById(id);
    }


    public AnswerDTO createAnswer (AnswerDTO answerDTO) throws BadRequestAlertException {

        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
            throw new BadRequestAlertException("Yalnızca Admin Rolü cevap ekleyebilir!! ", null, "test");
        }

        Answer answer = answerMapper.answerDTOToAnswer(answerDTO);
        answer = answerRepository.save(answer);
        return answerMapper.answerToAnswerDTO(answer);
    }

    public List<AnswerDTO> getAnswer(){
        List<AnswerDTO>  answerDTOList = answerRepository.findAll().stream().map(answerMapper::answerToAnswerDTO).collect(Collectors.toCollection(LinkedList::new));
        return answerDTOList;
    }



}
