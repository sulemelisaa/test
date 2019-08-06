package tr.com.metix.testproject.web.rest;


import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.repository.TestRepository;
import tr.com.metix.testproject.service.TestService;
import tr.com.metix.testproject.service.dto.TestDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TestResource {

    private final TestService testService;
    private final TestRepository testRepository;

    public TestResource(TestService testService, TestRepository testRepository) {
        this.testService = testService;
        this.testRepository = testRepository;
    }

    @PostMapping("/testcreate")
    public Test createTest(@Valid @RequestBody TestDTO testDTO) {

        Test test = testService.createTest(testDTO);
        return test;
    }

    @DeleteMapping("/deletetest/{id}")
    public void deleteTest(@PathVariable Long id) {

        testService.deleteTest(id);
    }

    @PutMapping("/testupdate")
    public Optional<TestDTO> updateTest(@Valid @RequestBody TestDTO testDTO) {

        Optional<TestDTO> updateTest = testService.updateTest(testDTO);

        return updateTest;

    }

    @GetMapping("/tests")
    public List<TestDTO> selectTest() {
        return testService.getTest();
    }

}
