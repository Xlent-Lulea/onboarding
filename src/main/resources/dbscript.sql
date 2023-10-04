-- Skapa upp en databas som går att använda för att testa applikationen.

CREATE TABLE TASK (
  id BIGSERIAL PRIMARY KEY,
  tasktype VARCHAR(255),
  urltitle VARCHAR(255),
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






