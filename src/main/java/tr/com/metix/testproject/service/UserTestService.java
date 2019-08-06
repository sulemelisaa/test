package tr.com.metix.testproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.domain.UserTest;
import tr.com.metix.testproject.repository.UserRepository;
import tr.com.metix.testproject.repository.UserTestRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.UserTestDTO;
import tr.com.metix.testproject.service.mapper.UserTestMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserTestService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserTestRepository userTestRepository;
    private final UserTestMapper userTestMapper;

    public UserTestService(UserService userService, UserRepository userRepository, UserTestRepository userTestRepository, UserTestMapper userTestMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userTestRepository = userTestRepository;
        this.userTestMapper = userTestMapper;
    }

    public List<UserTestDTO> getUserTest(){
        List<UserTestDTO>  userTest = userTestRepository.findAll().stream().map(userTestMapper::userTestToUserTestDTO).collect(Collectors.toCollection(LinkedList::new));
        return userTest;
    }

    public void deleteUserTest(Long id) throws BadRequestAlertException {
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
            throw new BadRequestAlertException("Yalnızca Admin Rolü  silebilir!! ", null, "test");
        }

        userTestRepository.deleteById(id);

    }

    public UserTestDTO createUserTest (UserTestDTO userTestDTO) throws BadRequestAlertException {

        // Current User
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        System.out.print("curret yetisiiii: " +u.get().getAuthorities());
        //current'ın user tablosundaki satırı
        Optional<User> user = userRepository.findById(u.get().getId());
        System.out.print("user yetkisiiii: " + user.get().getAuthorities());

        //    Question question = new Question();


        UserTest userTest = userTestMapper.userTestDTOToUserTest(userTestDTO);
        userTest = userTestRepository.save(userTest);
        return userTestMapper.userTestToUserTestDTO(userTest);
    }
}
