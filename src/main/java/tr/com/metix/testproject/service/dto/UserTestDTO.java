package tr.com.metix.testproject.service.dto;

import java.util.Set;

public class UserTestDTO {

    private Long id;
    private String status;
    private Long score;
    private Set<UserDTO> userDTOS;
    private Set<TestDTO> testDTOS;

    public Set<UserDTO> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(Set<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }

    public Set<TestDTO> getTestDTOS() {
        return testDTOS;
    }

    public void setTestDTOS(Set<TestDTO> testDTOS) {
        this.testDTOS = testDTOS;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
