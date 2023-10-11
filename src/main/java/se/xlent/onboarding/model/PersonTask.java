package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.PersonTaskEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonTask {

    @Schema(example = "1", required = true, description = "Id of PersonTask")
    private Long id;

    @Schema(example = "1", required = true, description = "Id of Task")
    private Long taskId;

    @Schema(example = "1", required = true, description = "Id of Person")
    private Long personId;

    @Schema(example = "true", required = true, description = "Is the task completed")
    private boolean isCompleted;
}


