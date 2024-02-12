package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PersonTaskEntityTest {

    @Test
    void testUpdatePersonTaskValues() {
        PersonTaskEntity personTask = new PersonTaskEntity();
        personTask.setIsCompleted(true);
        TaskEntity task = new TaskEntity();
        PersonEntity person = new PersonEntity();

        personTask.updatePersonTaskValues(task, person);

        assertEquals(personTask.getTask(), task);
        assertEquals(personTask.getPerson(), person);
        assertFalse(personTask.getIsCompleted());
    }
}
