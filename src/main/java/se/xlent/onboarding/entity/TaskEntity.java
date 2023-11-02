package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "TITLE")
    private String title;

    @NotBlank
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "URL")
    private String url;
}