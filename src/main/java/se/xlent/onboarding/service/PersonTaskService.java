package se.xlent.onboarding.service;

import se.xlent.onboarding.model.PersonTask;

import java.util.List;

public interface PersonTaskService {
    List<PersonTask> getAllByPersonId(Long personId);

    List<PersonTask> getAllByTaskId(Long taskId);

    PersonTask getById(Long id);

    PersonTask getByPersonIdAndTaskId(Long personId, Long taskId);

    PersonTask save(PersonTask personTask);

    PersonTask toggleTaskCompletionStatus(Long taskId);

    PersonTask reset(PersonTask task);

    void delete(Long id);
}
