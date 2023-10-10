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
    // private Long taskId;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;
    //private Long personId;

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

    public void setTaskId(Long value) {
        //this.taskId = value;
    }

    public Long getPersonId() {
        //return personId;
        return person.getId();
    }

    public void setPersonId(Long value) {
        //this.personId = value;

    }

    public void setPerson(PersonEntity value) {
        this.person = value;
    }
}
