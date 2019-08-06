package tr.com.metix.testproject.web.rest;


import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.service.UserTestService;
import tr.com.metix.testproject.service.dto.UserTestDTO;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserTestResource {

    private final UserTestService userTestService;

    public UserTestResource(UserTestService userTestService) {
        this.userTestService = userTestService;
    }

    @GetMapping("/usertest")
    public List<UserTestDTO> selectUserTest() {
        return userTestService.getUserTest();
    }

    @DeleteMapping("/deleteusertest/{id}")
    public void deleteUserTest(@PathVariable Long id) {

        userTestService.deleteUserTest(id);
    }
    @PostMapping("/createuserTest")
    public UserTestDTO createUserTest (@RequestBody UserTestDTO userTestDTO) throws URISyntaxException {


        UserTestDTO uDTO = userTestService.createUserTest(userTestDTO);
        return uDTO;
    }
    @PutMapping("/updateuserTest")
    public UserTestDTO updateUserTest (@RequestBody UserTestDTO userTestDTO) throws URISyntaxException {
        if (userTestDTO.getId() == null) {
            throw new BadRequestAlertException("geçersiz ID ! ", null, "idnull");
        }
        UserTestDTO uDTO = userTestService.createUserTest(userTestDTO);
        return uDTO;
    }


}
