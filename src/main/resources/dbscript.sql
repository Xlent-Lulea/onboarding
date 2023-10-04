-- Skapa upp en databas som går att använda för att testa applikationen.
CREATE TABLE PERSON (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  is_active BOOLEAN
);

CREATE TABLE PERSON-TASK (
  id BIGSERIAL PRIMARY KEY,
  task_id BIGINT REFERENCES TASK(id),
  person_id BIGINT REFERENCES PERSON(id),
  is_completed BOOLEAN
);

CREATE TABLE TASK (
  id BIGSERIAL PRIMARY KEY,
  type BIGINT REFERENCES TASK-TYPE(id),
  title VARCHAR(255),
  description VARCHAR(255),
  url VARCHAR(255)
);

CREATE TABLE TASK-TYPE (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255),
  task_id BIGINT REFERENCES TASK(id),
);

-- Lägg till person i Postman via POST 127.0.0.1:8081/createPerson och få standardtask (PersonController.java)

--{
--      "name": "Hejsan Svejsan",
--      "email": "john.doe@exsade.com"
--}






