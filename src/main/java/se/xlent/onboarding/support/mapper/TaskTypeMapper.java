package se.xlent.onboarding.support.mapper;

import org.mapstruct.Mapper;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.model.TaskType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskTypeMapper {

    TaskTypeEntity toEntity(TaskType taskType);

    TaskType toModel(TaskTypeEntity taskTypeEntity);

    List<TaskTypeEntity> toEntityList(List<TaskType> taskTypes);

    List<TaskType> toModelList(List<TaskTypeEntity> taskTypeEntities);
}
