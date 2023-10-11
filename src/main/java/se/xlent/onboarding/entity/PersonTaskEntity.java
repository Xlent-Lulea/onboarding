package se.xlent.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "PERSON_TASK")
public class PersonTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private TaskEntity task;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    @Column(name = "IS_COMPLETED")
    private Boolean isCompleted;

    public Long getId() {
        return id;
    }

    public Boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(Boolean value) {
        this.isCompleted = value;
    }

    @JsonIgnore
    public Long getTaskId() {
        return task.getId();
    }

    public void setTask(TaskEntity value) {
        this.task = value;
    }

    @JsonIgnore
    public Long getPersonId() {
        return person.getId();
    }

    public void setPerson(PersonEntity value) {
        this.person = value;
    }

    public PersonTaskEntity updatePersonTaskValues(TaskEntity task, PersonEntity person) {
        this.setPerson(person);
        this.setTask(task);
        this.setIsCompleted(false);
        return this;
    }
}
