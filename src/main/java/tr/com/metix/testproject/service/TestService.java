package tr.com.metix.testproject.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.TestRepository;
import tr.com.metix.testproject.repository.UserRepository;
import tr.com.metix.testproject.security.SecurityUtils;
import tr.com.metix.testproject.service.dto.TestDTO;
import tr.com.metix.testproject.service.mapper.TestMapper;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final TestMapper testMapper;

    public TestService(UserService userService, UserRepository userRepository, TestRepository testRepository, TestMapper testMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.testRepository = testRepository;
        this.testMapper = testMapper;
    }

    public Test createTest(TestDTO testDTO) throws BadRequestAlertException {

        // Current User
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        System.out.print("current yetkisiiii: " +u.get().getAuthorities());
        //current'ın user tablosundaki satırı
        Optional<User> user = userRepository.findById(u.get().getId());
        System.out.print("user yetkisiiii: " + user.get().getAuthorities());

        Test test = new Test();


        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
 //           System.out.print("\n hatalı: " + user.get().getAuthorities());
 //           System.out.print("\n hatalı id: " + u.get().getId() + "\n ");
            throw new BadRequestAlertException("Yalnızca Admin Rolü Test ekleyebilir!! ", null, "test");
        }
 //       System.out.print("\n hatasız: " + user.get().getAuthorities());
 //       System.out.print("\n hatasız id: " + u.get().getId() + "\n ");
        test.setCategory(testDTO.getCategory());
        test.setName(testDTO.getName());

        testRepository.save(test);
        return test;
    }

    public void deleteTest(Long id) throws BadRequestAlertException {
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
                 System.out.print("\n hatalı: " + user.get().getAuthorities());
                 System.out.print("\n hatalı id: " + u.get().getId() + "\n ");
            throw new BadRequestAlertException("Yalnızca Admin Rolü Test silebilir!! ", null, "test");
        }
        System.out.print("\n hatasız: " + user.get().getAuthorities());
        System.out.print("\n hatasız id: " + u.get().getId() + "\n ");
        testRepository.deleteById(id);

    }

    public Optional<TestDTO> updateTest(TestDTO testDTO) {
        Optional<User> u = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<User> user = userRepository.findById(u.get().getId());

        if (user.get().getAuthorities().stream().filter(x->x.getName().equals("ROLE_ADMIN")).collect(Collectors.toList()).isEmpty()) {
            throw new BadRequestAlertException("Yalnızca Admin Rolü Test silebilir!! ", null, "test");
        }

        return Optional.of(testRepository
            .findById(testDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(test -> {

                test.setName(testDTO.getName());
                test.setCategory(testDTO.getCategory());
                return test;
            })
            .map(testMapper::testToTestDTO);
    }

    public List<TestDTO> getTest(){
        List<TestDTO>  test = testRepository.findAll().stream().map(testMapper::testToTestDTO).collect(Collectors.toCollection(LinkedList::new));
        return test;
    }

}


