package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskType {

    @Schema(example = "1", description = "Id of the task type")
    private Long id;

    @NotBlank
    @Schema(example = "Välkommen", description = "Name of the task type")
    private String name;

    @Schema(example = "false", description = "Represents if the taskType is for buddy or not")
    private Boolean isBuddyType;
}
