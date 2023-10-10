package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.TaskEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Schema(example = "1", required = true, description = "Id of the task")
    private Long id;

    @Schema(example = "1", required = true, description = "Id of the task type")
    private Long typeId;

    @Schema(example = "Task 1", required = false, description = "Title of the task")
    private String title;

    @Schema(example = "This is a task", required = true, description = "Description of the task")
    private String description;

    @Schema(example = "www.xlent.se", required = false, description = "Url of the task")
    private String url;

    public static Task taskBuilder(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .typeId(entity.getTypeId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .url(entity.getUrl())
                .build();
    }
}



