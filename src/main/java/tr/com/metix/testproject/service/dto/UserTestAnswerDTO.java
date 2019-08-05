package tr.com.metix.testproject.service.dto;

import java.util.Set;

public class UserTestAnswerDTO {

    private Long id;
    private Set<UserTestDTO> userTestDTOS;
    private Long answerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserTestDTOS(Set<UserTestDTO> userTestDTOS) {
        this.userTestDTOS = userTestDTOS;
    }

    public Set<UserTestDTO> getUserTestDTOS() {
        return userTestDTOS;
    }

    public void setUserTestId(Set<UserTestDTO> userTestDTOS) {
        this.userTestDTOS = userTestDTOS;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
