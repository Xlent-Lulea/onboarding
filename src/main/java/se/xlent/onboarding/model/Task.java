package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Schema(example = "1", required = true, description = "Id of the task")
    private Long id;

    @Schema(example = "1", required = true, description = "Id of the task type")
    private Long typeId;

    @NotBlank
    @Schema(example = "Task 1", required = false, description = "Title of the task")
    private String title;

    @NotBlank
    @Schema(example = "This is a task", required = true, description = "Description of the task")
    private String description;

    @Schema(example = "www.xlent.se", required = false, description = "Url of the task")
    private String url;
}



