package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Schema(example = "1", required = true, description = "Id of the person")
    private Long id;
    @Schema(example = "Marcus Sundbom", required = true, description = "Name of the person")
    private String name;

    @Schema(example = "marcus.sundbom@xlent.se", required = true, description = "Email of the person")
    private String email;

    private List<TaskEntity> taskEntities = new ArrayList<>();

    @Schema(example = "true", required = true, description = "Active status of the person")
    private boolean active;

    public static Person persons(PersonEntity entity) {
        return Person.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .active(entity.isActive())
                .build();
    }

}
