package se.xlent.onboarding.service;

import se.xlent.onboarding.model.Person;
import java.util.List;

public interface PersonService {
    Person create(Person person);
    Person save(Person person);

    Person getById(Long id);

    List<Person> getActivePersons();

    List<Person> getAll();

    void delete(Long id);

    void resetTasks(Person person);
}
