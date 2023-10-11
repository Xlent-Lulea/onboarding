package se.xlent.onboarding.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;

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

    @Schema(example = "[]", required = true, description = "List")
    private List<PersonTaskEntity> tasks = new ArrayList<>();

    @Schema(example = "true", required = true, description = "Active status of the person")
    private boolean isActive;
}
