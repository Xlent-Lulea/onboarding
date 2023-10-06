package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.PersonTaskRepository;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonEntity saveUpdatePerson(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    @Override
    public PersonEntity findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonEntity> getActivePersons() {
        return personRepository.findByActiveTrue();
    }

    @Override
    public List<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<PersonEntity> getInactivePersons() {
        return personRepository.findByActiveFalse();
    }

    @Override
    public void deletePerson(PersonEntity personEntity) {
        personRepository.delete(personEntity);
    }

    @Autowired
    private PersonTaskRepository personTaskRepository;

    @Override
    public PersonTaskEntity savePersonTask(PersonTaskEntity personTask) {
        return personTaskRepository.save(personTask);
    }


}
