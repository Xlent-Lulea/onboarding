package se.xlent.onboarding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Column(name="NAME")
    private String name;

    @Getter
    @Email
    @NotBlank
    @Column(name="EMAIL")
    private String email;

    @Getter
    @Setter
    @Column(name="IS_ACTIVE")
    private Boolean isActive = true;
}