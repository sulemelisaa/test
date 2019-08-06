package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.service.AnswerService;
import tr.com.metix.testproject.service.dto.AnswerDTO;
import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerResource {

    private final AnswerService answerService;

    public AnswerResource(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/answer")
    public List<AnswerDTO> selectAnswer() {
        return answerService.getAnswer();
    }

    @DeleteMapping("/deleteanswer/{id}")
    public void deleteAnswer(@PathVariable Long id) {

        answerService.deleteAnswer(id);
    }

    @PostMapping("/createanswer")
    public AnswerDTO createAnswer(@RequestBody AnswerDTO answerDTO) throws URISyntaxException {

        if (answerDTO.getId() != null) {
            throw new BadRequestAlertException("Bu id'ye sahip cevap zaten kayır edilmiş !! ", null, "idexists");
        }
        AnswerDTO aDTO = answerService.createAnswer(answerDTO);
        return aDTO;
    }

    @PutMapping("/updateanswer")
    public AnswerDTO updateAnswer (@RequestBody AnswerDTO answerDTO) throws URISyntaxException {
        if (answerDTO.getId() == null) {
            throw new BadRequestAlertException("geçersiz ID ! ", null, "idnull");
        }
        AnswerDTO aDTO = answerService.createAnswer(answerDTO);
        return aDTO;
    }
}
