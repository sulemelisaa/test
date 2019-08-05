package tr.com.metix.testproject.service.dto;

import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.UserTest;

import java.util.Set;

public class UserTestAnswerDTO {

    private Long userTestAnsverid;
    private Set<UserTestDTO> userTestId;
    private Long answerId;


    public Long getUserTestAnsverid() {
        return userTestAnsverid;
    }

    public void setUserTestAnsverid(Long userTestAnsverid) {
        this.userTestAnsverid = userTestAnsverid;
    }

    public Set<UserTestDTO> getUserTestId() {
        return userTestId;
    }

    public void setUserTestId(Set<UserTestDTO> userTestId) {
        this.userTestId = userTestId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
