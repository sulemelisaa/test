package tr.com.metix.testproject.web.rest;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.service.TestService;
import tr.com.metix.testproject.service.dto.TestDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TestResource {

    private final TestService testService;

    public TestResource(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/testcreate")
    public Long createTest(@Valid @RequestBody TestDTO testDTO){

        Long test = testService.createTest(testDTO);

        return 1L;
    }
}
