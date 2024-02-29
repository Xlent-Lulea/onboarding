package se.xlent.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PERSON_TASK")
public class PersonTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "TASK_ID")
    private TaskEntity task;

    @JsonIgnore
    @ManyToOne
    @NotNull
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    @Column(name = "IS_COMPLETED")
    private Boolean isCompleted;
}
