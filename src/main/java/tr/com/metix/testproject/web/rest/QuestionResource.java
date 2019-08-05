package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.service.QuestionService;
import tr.com.metix.testproject.service.dto.QuestionDTO;
import tr.com.metix.testproject.service.dto.TestDTO;

import javax.validation.Valid;
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
    public Question createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {

        Question question = questionService.q(testDTO);

        return question;
    }



}
