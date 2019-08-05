package tr.com.metix.testproject.service.dto;

import java.util.Set;

public class QuestionDTO {
    private Long questionId;
    private String value;
    private Set<TestDTO> testId;


    public Long getId() {
        return questionId;
    }

    public void setId(Long id) {
        this.questionId = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Set<TestDTO> getTestId() {
        return testId;
    }

    public void setTestId(Set<TestDTO> testId) {
        this.testId = testId;
    }
}
