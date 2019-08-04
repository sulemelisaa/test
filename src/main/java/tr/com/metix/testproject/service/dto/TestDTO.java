package tr.com.metix.testproject.service.dto;

public class TestDTO {

    private Long testid;
    private String testname;
    private String testcategory;

    public Long getTestid() {
        return testid;
    }

    public void setTestid(Long testid) {
        this.testid = testid;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getTestcategory() {
        return testcategory;
    }

    public void setTestcategory(String testcategory) {
        this.testcategory = testcategory;
    }
}
