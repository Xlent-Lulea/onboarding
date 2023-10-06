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

    @Column(name = "IS_COMPLETED")
    private Boolean isCompleted;

}
