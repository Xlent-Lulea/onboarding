package se.xlent.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "PERSON_TASK")
public class PersonTaskEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private TaskEntity task;

    @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    @Getter
    @Setter
    @Column(name = "IS_COMPLETED")
    private Boolean isCompleted;

    public PersonTaskEntity updatePersonTaskValues(TaskEntity task, PersonEntity person) {
        this.setPerson(person);
        this.setTask(task);
        this.setIsCompleted(false);
        return this;
    }
}
