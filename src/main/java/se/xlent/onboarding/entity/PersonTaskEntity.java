package se.xlent.onboarding.entity;

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

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;
    // private Long personId;

    @Column(name = "IS_COMPLETED")
    private Boolean isCompleted;

    public Long getId() {
        return id;
    }

    public Boolean getCompletionStatus() {
        return this.isCompleted;
    }

    public void setCompletionStatus(Boolean value) {
        this.isCompleted = value;
    }

    public Long getTaskId() {
        // return taskId;
        return task.getId();
    }

    public void setTask(TaskEntity value) {
        this.task = value;
    }

    public Long getPersonId() {
        //return personId;
        return person.getId();
    }

    public void setPerson(PersonEntity value) {
        this.person = value;
    }

    public PersonTaskEntity updatePersonTaskValues(TaskEntity task, PersonEntity person) {
        this.setPerson(person);
        this.setTask(task);
        this.setCompletionStatus(false);
        return this;
    }
}
