package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonTask {

    @Schema(example = "1", required = true, description = "Id of PersonTask")
    private Long id;

    @Schema(example = "1", required = true, description = "Id of Task")
    private TaskEntity task;

    @Schema(example = "1", required = true, description = "Id of Person")
    private PersonEntity person;

    @Schema(example = "true", required = true, description = "Is the task completed")
    private boolean isCompleted;

    public static PersonTask personTasks(PersonTaskEntity entity) {
        return PersonTask.builder()
                .id(entity.getId())
                .task(entity.getTask())
                .person(entity.getPerson())
                .isCompleted(entity.getIsCompleted())
                .build();
    }





}


