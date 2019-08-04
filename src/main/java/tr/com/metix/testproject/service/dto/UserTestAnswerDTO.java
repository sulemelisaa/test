package tr.com.metix.testproject.service.dto;

import tr.com.metix.testproject.domain.Answer;

import java.util.Set;

public class UserTestAnswerDTO {

    private Long id;
    private Set<Long> usertestid;
    private Long answerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getUsertestid() {
        return usertestid;
    }

    public void setUsertestid(Set<Long> usertestid) {
        this.usertestid = usertestid;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }


}
