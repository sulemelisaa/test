package tr.com.metix.testproject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usertestanswer")
public class UserTestAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "usertestanswer_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "usertestanswer_usertest",
        joinColumns = @JoinColumn(name = "usertestanswer_id"),
        inverseJoinColumns = @JoinColumn(name = "usertest_id"))
    private Set<UserTest> usertest = new HashSet<>();

    @ManyToOne
    private Answer answerId;

    public Set<UserTest> getUsertest() {
        return usertest;
    }

    public void setUsertest(Set<UserTest> usertest) {
        this.usertest = usertest;
    }

    public Answer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Answer answerId) {
        this.answerId = answerId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
