-- Skapa upp en databas som går att använda för att testa applikationen.

CREATE TABLE TASK (
  id BIGSERIAL PRIMARY KEY,
  tasktype VARCHAR(255),
  title VARCHAR(255),
  description VARCHAR(255),
  url VARCHAR(255),
  completed BOOLEAN,
  person_id BIGINT
);


CREATE TABLE PERSON (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  active BOOLEAN
);

-- Lägg till person i Postman via POST 127.0.0.1:8081/createPerson och få standardtask (PersonController.java)

--{
--      "name": "Hejsan Svejsan",
--      "email": "john.doe@exsade.com"
--}




INSERT INTO PERSON (name, email, active) VALUES ('test1', 'test1@example.com', true);
INSERT INTO PERSON (name, email, active) VALUES ('test2', 'test2@example.com', false);
INSERT INTO PERSON (name, email, active) VALUES ('test3', 'test3@example.com', true);


INSERT INTO TASK (TaskType, title, description, completed, person_id) VALUES ('BEFORE_START', '1Beställ blommor', 'Blommor beställs från Interflora.', false, 1);
INSERT INTO TASK (TaskType, title, description, completed, person_id) VALUES ('AFTER_START_BUDDY', '1Beställ tårta', 'Tårta beställs från Tårtan.', false, 1);
INSERT INTO TASK (TaskType, title, description, completed, person_id) VALUES ('AFTER_START_RECRUIT', '1Beställ kaffe', 'Kaffe beställs från Kaffekompaniet.', false, 1);



