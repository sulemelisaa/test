package tr.com.metix.testproject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "iscorrect")
    private boolean isCorrect;

    @ManyToMany
    @JoinTable(name = "answer_question",
        joinColumns = @JoinColumn(name = "answer_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> question = new HashSet<>();

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }


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



}
