package tr.com.metix.testproject.service.dto;

import tr.com.metix.testproject.domain.Question;

import java.util.Set;

public class AnswerDTO {
    private Long id;
    private String value;
    private boolean isCorrect;
    private Set<Long> questionid;

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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Set<Long> getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Set<Long> questionid) {
        this.questionid = questionid;
    }
}
