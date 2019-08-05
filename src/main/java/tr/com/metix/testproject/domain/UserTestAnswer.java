package tr.com.metix.testproject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user_test_answer")
public class UserTestAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "user_test_answer_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "usertestanswer_usertest",
        joinColumns = @JoinColumn(name = "user_test_answer_id"),
        inverseJoinColumns = @JoinColumn(name = "user_test_id"))
    private Set<UserTest> usertest = new HashSet<>();

    @ManyToOne
    private Answer answer;

    public Set<UserTest> getUsertest() {
        return usertest;
    }

    public void setUsertest(Set<UserTest> usertest) {
        this.usertest = usertest;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
