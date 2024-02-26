package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.repository.PersonTaskRepository;
import se.xlent.onboarding.support.mapper.PersonTaskMapper;

import java.util.List;

@Service
public class PersonTaskServiceImpl implements PersonTaskService {

    @Autowired
    private PersonTaskRepository personTaskRepository;
    @Autowired
    private PersonTaskMapper personTaskMapper;

    public List<PersonTask> getAllByPersonId(Long personId) {
        return personTaskMapper.toModelList(personTaskRepository.findByPersonId(personId));
    }

    public List<PersonTask> getAllByTaskId(Long taskId) {
        return personTaskMapper.toModelList(personTaskRepository.findByTaskId(taskId));
    }

    public PersonTask getById(Long id) {
        return personTaskMapper.toModel(personTaskRepository.findById(id).orElse(null));
    }

    public PersonTask getByPersonIdAndTaskId(Long personId, Long taskId) {
        return personTaskMapper.toModel(personTaskRepository.findByPersonIdAndTaskId(personId, taskId));
    }

    public PersonTask save(PersonTask personTask) {
        return personTaskMapper.toModel(personTaskRepository.save(personTaskMapper.toEntity(personTask)));
    }

    public PersonTask toggleTaskCompletionStatus(Long taskId) {
        PersonTask personTask = getById(taskId);

        if (personTask == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonTask with id " + taskId + " not found");
        }

        personTask.setIsCompleted(!personTask.getIsCompleted());
        return personTaskMapper.toModel(personTaskRepository.save(personTaskMapper.toEntity(personTask)));
    }

    public PersonTask reset(PersonTask task) {
        if (task.getIsCompleted()) {
            task.setIsCompleted(false);
            task = personTaskMapper.toModel(personTaskRepository.save(personTaskMapper.toEntity(task)));
        }

        return task;
    }

    public void delete(Long id) {
        personTaskRepository.deleteById(id);
    }
}
