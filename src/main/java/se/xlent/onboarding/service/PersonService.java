package se.xlent.onboarding.service;

import se.xlent.onboarding.entity.PersonEntity;

import java.util.List;


public interface PersonService {
    public PersonEntity saveUpdatePerson(PersonEntity personEntity);
    public PersonEntity findPersonById(Long id);

    List<PersonEntity> getActivePersons();

    List<PersonEntity> getAllPersons();

    List<PersonEntity> getInactivePersons();

}

