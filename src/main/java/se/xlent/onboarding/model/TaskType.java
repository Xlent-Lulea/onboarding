package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.TaskTypeEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskType {

    @Schema(example = "1", required = true, description = "Id of the task type")
    private Long id;
    @Schema(example = "Välkommen", required = true, description = "Name of the task type")
    private String name;
}
