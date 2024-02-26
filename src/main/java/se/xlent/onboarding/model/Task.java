package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Schema(example = "1", description = "Id of the task")
    private Long id;

    @NotNull
    @Schema(example = "1", description = "Tasktype the task belongs to")
    private TaskType type;

    @NotBlank
    @Schema(example = "Task 1", description = "Title of the task")
    private String title;

    @NotBlank
    @Schema(example = "This is a task", description = "Description of the task")
    private String description;

    @Schema(example = "www.xlent.se",  description = "Url of the task")
    private String url;
}



