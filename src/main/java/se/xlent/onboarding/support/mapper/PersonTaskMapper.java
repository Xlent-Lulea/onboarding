package se.xlent.onboarding.support.mapper;

import org.mapstruct.Mapper;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.model.PersonTask;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, TaskMapper.class})
public interface PersonTaskMapper {
    PersonTaskEntity toEntity(PersonTask personTask);

    PersonTask toModel(PersonTaskEntity personTaskEntity);

    List<PersonTaskEntity> toEntityList(List<PersonTask> personTasks);

    List<PersonTask> toModelList(List<PersonTaskEntity> personTaskEntities);
}
