package tr.com.metix.testproject.service.dto;

import java.util.Set;

public class QuestionDTO {
    private Long id;
    private String value;

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

    public Set<Long> getTestid() {
        return testid;
    }

    public void setTestid(Set<Long> testid) {
        this.testid = testid;
    }

    private Set<Long> testid;


}
