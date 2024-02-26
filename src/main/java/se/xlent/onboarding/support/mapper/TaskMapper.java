package se.xlent.onboarding.support.mapper;

import org.mapstruct.Mapper;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.model.Task;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskTypeMapper.class)
public interface TaskMapper {
    TaskEntity toEntity(Task task);

    Task toModel(TaskEntity taskEntity);

    List<TaskEntity> toEntityList(List<Task> tasks);

    List<Task> toModelList(List<TaskEntity> taskEntities);
}
