package tr.com.metix.testproject.service.dto;

import tr.com.metix.testproject.domain.Question;

import java.util.Set;

public class AnswerDTO {
    private Long answerId;
    private String value;
    private boolean isCorrect;
    private Set<QuestionDTO> questionId;

    public Long getId() {
        return answerId;
    }

    public void setId(Long id) {
        this.answerId = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Set<QuestionDTO> getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Set<QuestionDTO> questionId) {
        this.questionId = questionId;
    }
}
