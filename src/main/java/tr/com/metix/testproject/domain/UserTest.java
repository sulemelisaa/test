package tr.com.metix.testproject.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_test")
public class UserTest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name="id")
    private Long id;

    @Column(name="status")
    private String status;

    @Column(name="score")
    private Long score;

     @ManyToMany
     @JoinTable(name = "usertest_user",
         joinColumns = @JoinColumn(name = "user_test_id"),
         inverseJoinColumns = @JoinColumn(name = "user_id"))
     private Set<User> user = new HashSet<>();

      @ManyToMany
      @JoinTable(name = "usertest_test",
          joinColumns = @JoinColumn(name = "user_test_id"),
          inverseJoinColumns = @JoinColumn(name = "test_id"))
      private Set<Test> test = new HashSet<>();

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<Test> getTest() {
        return test;
    }

    public void setTest(Set<Test> test) {
        this.test = test;
    }

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

}
