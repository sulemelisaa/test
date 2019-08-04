package tr.com.metix.testproject.service.dto;

import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.domain.User;

import java.util.Set;

public class UserTestDTO {

    private Long id;
    private String status;
    private Long score;
    private Set<Long> userid;
    private Set<Long> testid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Long> getUserid() {
        return userid;
    }

    public void setUserid(Set<Long> userid) {
        this.userid = userid;
    }

    public Set<Long> getTestid() {
        return testid;
    }

    public void setTestid(Set<Long> testid) {
        this.testid = testid;
    }
}
