package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Schema(example = "1", description = "Id of the person")
    private Long id;

    @NotBlank
    @Schema(example = "John Doe", description = "Name of the person")
    private String name;

    @NotBlank
    @Email
    @Schema(example = "john.doe@xlent.se", description = "Email of the person")
    private String email;

    @Schema(example = "true", description = "Active status of the person")
    private Boolean isActive;

    @Schema(description = "Mentee of the person. If the person is not a buddy, mentee is null")
    private Person mentee;
}
