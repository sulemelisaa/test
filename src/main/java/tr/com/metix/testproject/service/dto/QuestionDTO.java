package tr.com.metix.testproject.service.dto;

import java.util.Set;

public class QuestionDTO {
    private Long id;
    private String value;

    private Set<TestDTO> testDTOS;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<TestDTO> getTestDTOS() {
        return testDTOS;
    }

    public void setTestDTOS(Set<TestDTO> testDTOS) {
        this.testDTOS = testDTOS;
    }
}
