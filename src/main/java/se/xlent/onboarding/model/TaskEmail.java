package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskEmail {

    @Schema(example = "onboarding@xlent.se", required = true, description = "Email of the person")
    private String to;

    @Schema(example = "Task 1", required = true, description = "Title of the task")
    private String title;

    @Schema(example = "This is a task", required = true, description = "Description of the task")
    private String description;

    @Schema(example = "www.xlent.se", required = true, description = "Url of the task")
    private String url;

    @Schema(example = "Additional info", required = true, description = "Additional info of the task")
    private String additionalInfo;
}
