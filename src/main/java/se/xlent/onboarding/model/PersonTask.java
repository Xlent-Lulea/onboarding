package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonTask {

    @Schema(example = "1", description = "Id of the personTask")
    private Long id;

    @NotNull
    @Schema(description = "The task which the personTask maps to")
    private Task task;

    @NotNull
    @Schema(description = "The person which the personTask belongs to")
    private Person person;

    @Schema(example = "true", description = "Representing if the task is completed or not")
    private Boolean isCompleted;

    public PersonTask updatePersonTaskValues(Task task, Person person) {
        this.setPerson(person);
        this.setTask(task);
        this.setIsCompleted(false);
        return this;
    }
}


