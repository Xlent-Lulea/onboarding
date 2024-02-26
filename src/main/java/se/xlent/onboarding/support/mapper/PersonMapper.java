package se.xlent.onboarding.support.mapper;

import org.mapstruct.Mapper;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.model.Person;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonEntity toEntity(Person person);

    Person toModel(PersonEntity personEntity);

    List<PersonEntity> toEntityList(List<Person> persons);

    List<Person> toModelList(List<PersonEntity> personEntities);
}
