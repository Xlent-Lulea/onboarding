package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PERSON")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name="NAME", columnDefinition = "VARCHAR(255)")
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @Column(name="EMAIL", columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name="IS_ACTIVE")
    private Boolean isActive = true;

    @OneToOne
    @JoinColumn(name = "MENTEE_ID", nullable = true)
    private PersonEntity mentee;
}