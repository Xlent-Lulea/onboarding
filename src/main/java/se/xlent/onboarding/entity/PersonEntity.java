package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "PERSON")
public class PersonEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @Size(max = 255)
    @Column(name="NAME", columnDefinition = "VARCHAR(255)")
    private String name;

    @Getter
    @Email
    @NotBlank
    @Size(max = 255)
    @Column(name="EMAIL", columnDefinition = "VARCHAR(255)")
    private String email;

    @Getter
    @Setter
    @Column(name="IS_ACTIVE")
    private Boolean isActive = true;
}