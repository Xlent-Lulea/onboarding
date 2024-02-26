package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TASK_TYPE")
public class TaskTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "NAME", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name="IS_BUDDY_TYPE")
    private Boolean isBuddyType = false;
}