package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskType {

    @Schema(example = "1", required = true, description = "Id of the task type")
    private Long id;

    @NotBlank
    @Schema(example = "Välkommen", required = true, description = "Name of the task type")
    private String name;
}
