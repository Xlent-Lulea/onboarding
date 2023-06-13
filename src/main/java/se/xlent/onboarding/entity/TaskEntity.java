package se.xlent.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "TASK")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="PERSON_ID")
    @JsonIgnore
    private PersonEntity person;
    @Enumerated(EnumType.STRING)
    @Column(name = "TASKTYPE")
    private TaskType taskType;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COMPLETED")
    private boolean completed;
    @Column(name = "URL")
    private String url;

}