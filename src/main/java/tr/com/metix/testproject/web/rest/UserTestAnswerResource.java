package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.domain.UserTestAnswer;
import tr.com.metix.testproject.service.UserTestAnswerService;
import tr.com.metix.testproject.service.dto.UserTestAnswerDTO;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserTestAnswerResource {

    private final UserTestAnswerService userTestAnswerService;

    public UserTestAnswerResource(UserTestAnswerService userTestAnswerService) {
        this.userTestAnswerService = userTestAnswerService;
    }

    @GetMapping("/usertestanswer")
    public List<UserTestAnswerDTO> selectUserTestAnswer() {
        return userTestAnswerService.getUserTestAnswer();
    }

    @DeleteMapping("/deleteusertestanswer/{id}")
    public void deleteUserTestAnswer(@PathVariable Long id) {

        userTestAnswerService.deleteUserTestAnswer(id);
    }

    @PostMapping("/createuserTestAnswer")
    public UserTestAnswerDTO createUserTestAnswer (@RequestBody UserTestAnswerDTO userTestAnswerDTO) throws URISyntaxException {


        UserTestAnswerDTO uDTO = userTestAnswerService.createUserTestAnswer(userTestAnswerDTO);
        return uDTO;
    }

    @PutMapping("/updateuserTestAnswer")
    public UserTestAnswerDTO updateUserTestAnswer (@RequestBody UserTestAnswerDTO userTestAnswerDTO) throws URISyntaxException {
        if (userTestAnswerDTO.getId() == null) {
            throw new BadRequestAlertException("geçersiz ID ! ", null, "idnull");
        }
        UserTestAnswerDTO uDTO = userTestAnswerService.createUserTestAnswer(userTestAnswerDTO);
        return uDTO;
    }
}
