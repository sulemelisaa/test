package tr.com.metix.testproject.service.dto;

import java.util.Set;

public class UserTestDTO {

    private Long userTestId;
    private String status;
    private Long score;
    private Set<UserDTO> userId;
    private Set<TestDTO> testId;

    public Long getId() {
        return userTestId;
    }

    public void setId(Long id) {
        this.userTestId = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getUserTestid() {
        return userTestId;
    }

    public void setUserTestid(Long userTestid) {
        this.userTestId = userTestid;
    }

    public Long getUserTestId() {
        return userTestId;
    }

    public void setUserTestId(Long userTestId) {
        this.userTestId = userTestId;
    }

    public Set<UserDTO> getUserId() {
        return userId;
    }

    public void setUserId(Set<UserDTO> userId) {
        this.userId = userId;
    }

    public Set<TestDTO> getTestId() {
        return testId;
    }

    public void setTestId(Set<TestDTO> testId) {
        this.testId = testId;
    }
}
