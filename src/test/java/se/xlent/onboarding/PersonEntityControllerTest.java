package se.xlent.onboarding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import se.xlent.onboarding.entity.PersonEntity;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Disabled
public class PersonEntityControllerTest {

    private static String createPersonUrl;
    private static String updatePersonUrl;
    private static RestTemplate restTemplate;
    private static HttpHeaders headers;
    private static JSONObject personJsonObject;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void runBeforeAllTestMethods() throws JSONException {
        createPersonUrl = "http://localhost:8081/spring-rest/createPerson";
        updatePersonUrl = "http://localhost:8081/spring-rest/updatePerson";

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        personJsonObject.put("id", 1);
        personJsonObject.put("name", "John");
    }

    // Add your test methods here
    @Test
    void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
            throws IOException {
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        String personResultAsJsonStr =
                restTemplate.postForObject(createPersonUrl, request, String.class);
        JsonNode root = objectMapper.readTree(personResultAsJsonStr);

        assertNotNull(personResultAsJsonStr);
        assertNotNull(root);
        assertNotNull(root.path("name").asText());

        PersonEntity personEntity = restTemplate.postForObject(createPersonUrl, request, PersonEntity.class);

        assertNotNull(personEntity);
        assertNotNull(personEntity.getName());
    }

    @Test
    void givenDataIsJson_whenDataIsPostedByPostForEntity_thenResponseBodyIsNotNull() {
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        ResponseEntity<PersonEntity> responseEntityPerson = restTemplate.
                postForEntity(createPersonUrl, request, PersonEntity.class);

        assertNotNull(responseEntityPerson.getBody());
        assertNotNull(responseEntityPerson.getBody().getName());
    }

    @Test
    void givenDataIsJson_whenDataIsPostedByPostForLocation_thenResponseBodyIsTheLocationHeader() {
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        URI locationHeader = restTemplate.postForLocation(updatePersonUrl, request);

        assertNotNull(locationHeader);
    }
}

