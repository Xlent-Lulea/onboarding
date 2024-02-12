package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.service.PersonService;
import se.xlent.onboarding.service.PersonTaskService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PersonService personService;


    @Test
    void testGetAll() throws Exception {
        when(personService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk());
    }
}
/*

        mockMvc.perform(get("/api/persons"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is((int) bookId)))
        .andExpect(jsonPath("$.title", is("Mock Book")))
        .andExpect(jsonPath("$.author", is("Author")));
*/