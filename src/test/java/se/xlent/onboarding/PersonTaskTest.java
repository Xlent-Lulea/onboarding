package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.model.TaskType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PersonTaskTest {

    @Test
    void testUpdatePersonTaskValues() {
        PersonTask personTask = new PersonTask();
        personTask.setIsCompleted(true);
        Task task = new Task();
        task.setType(new TaskType());
        Person person = new Person();

        personTask.updatePersonTaskValues(task, person);

        assertEquals(personTask.getTask(), task);
        assertEquals(personTask.getPerson(), person);
        assertFalse(personTask.getIsCompleted());
    }
}
