package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Schema(example = "1", required = true, description = "Id of the task")
    private Long id;

    @Schema(example = "BEFORE_START", required = true, description = "Task type of the task")
    private TaskType taskType;

    @Schema(example = "Task 1", required = true, description = "Title of the task")
    private String title;

    @Schema(example = "This is a task", required = true, description = "Description of the task")
    private String description;

    @Schema(example = "false", required = true, description = "Uncompleted status of the task")
    private boolean completed;

    @Schema(example = "www.xlent.se", required = true, description = "Url of the task")
    private String url;

    public static Task taskBuilder(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .taskType(entity.getTaskType())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .completed(entity.isCompleted())
                .url(entity.getUrl())
                .build();
    }
}



