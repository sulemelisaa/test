package tr.com.metix.testproject.web.rest;



import org.springframework.web.bind.annotation.*;


import tr.com.metix.testproject.service.QuestionService;
import tr.com.metix.testproject.service.dto.QuestionDTO;

import tr.com.metix.testproject.web.rest.errors.BadRequestAlertException;


import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final QuestionService questionService;

    public QuestionResource(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question")
    public List<QuestionDTO> selectQuestion() {
        return questionService.getQuestion();
    }


    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Long id) {

        questionService.deleteQuestion(id);
    }


    @PostMapping("/questioncreate")
    public QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO) throws URISyntaxException {

        if (questionDTO.getId() != null) {
            throw new BadRequestAlertException("Bu id'ye sahip soru zaten kayır edilmiş !! ", null, "idexists");
        }
        QuestionDTO qDTO = questionService.createQuestion(questionDTO);
        return qDTO;
    }

    @PutMapping("/questionupdate")
    public QuestionDTO updateQuestion (@RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        if (questionDTO.getId() == null) {
            throw new BadRequestAlertException("geçersiz ID ! ", null, "idnull");
        }
        QuestionDTO qDTO = questionService.createQuestion(questionDTO);
        return qDTO;
    }

}
