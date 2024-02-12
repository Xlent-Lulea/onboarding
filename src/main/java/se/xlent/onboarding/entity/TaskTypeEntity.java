package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Table(name = "TASK_TYPE")
public class TaskTypeEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Getter
    @NotBlank
    @Size(max = 255)
    @Column(name = "NAME", columnDefinition = "VARCHAR(255)")
    private String name;
}