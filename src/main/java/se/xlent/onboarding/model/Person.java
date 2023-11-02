package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Schema(example = "1", required = true, description = "Id of the person")
    private Long id;

    @NotBlank
    @Schema(example = "Marcus Sundbom", required = true, description = "Name of the person")
    private String name;

    @NotBlank
    @Email
    @Schema(example = "marcus.sundbom@xlent.se", required = true, description = "Email of the person")
    private String email;

    @Schema(example = "true", required = true, description = "Active status of the person")
    private Boolean isActive;
}
