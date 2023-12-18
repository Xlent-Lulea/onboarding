package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name="TYPE_ID")
    private TaskTypeEntity type;

    @NotBlank
    @Size(max = 255)
    @Column(name = "TITLE", columnDefinition = "VARCHAR(255)")
    private String title;

    @NotBlank
    @Size(max = 255)
    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(255)")
    private String description;

    @Size(max = 255)
    @Column(name = "URL", columnDefinition = "VARCHAR(255)")
    private String url;
}