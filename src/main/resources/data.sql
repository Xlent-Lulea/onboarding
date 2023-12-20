INSERT INTO TASK_TYPE (name)
VALUES ('Välkommen'),
        ('Buddy/Coach'),
        ('Startklar'),
        ('Anställning och admin'),
        ('Digital Setup'),
        ('Konsultrollen'),
        ('Avslut!');

-- Create a new person
INSERT INTO PERSON (name, email, is_active)
VALUES ('John Doe', 'john.doe@exsade.com', true);

-- Get the ID of the newly created person
SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com';

-- Create a task for each task type and associate them with the new person
INSERT INTO TASK (type_id, title, description, url)
VALUES
    (1, 'Task for Välkommen', 'Description for Välkommen task', 'http://example.com/task_välkommen'),
    (2, 'Task for Buddy/Coach', 'Description for Buddy/Coach task', 'http://example.com/task_buddy_coach'),
    (3, 'Task for Startklar', 'Description for Startklar task', 'http://example.com/task_startklar'),
    (4, 'Task for Anställning och admin', 'Description for Anställning och admin task', 'http://example.com/task_anställning_admin'),
    (5, 'Task for Digital Setup', 'Description for Digital Setup task', 'http://example.com/task_digital_setup'),
    (6, 'Task for Konsultrollen', 'Description for Konsultrollen task', 'http://example.com/task_konsultrollen'),
    (7, 'Task for Avslut!', 'Description for Avslut! task', 'http://example.com/task_avslut');

-- Get the IDs of the newly created tasks
SELECT id FROM TASK WHERE type_id IN (1, 2, 3, 4, 5, 6, 7);

-- Assign each task to the new person
INSERT INTO PERSON_TASK (task_id, person_id, is_completed)
VALUES
    (SELECT id FROM TASK WHERE type_id = 1, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false),
    (SELECT id FROM TASK WHERE type_id = 2, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false),
    (SELECT id FROM TASK WHERE type_id = 3, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false),
    (SELECT id FROM TASK WHERE type_id = 4, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false),
    (SELECT id FROM TASK WHERE type_id = 5, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false),
    (SELECT id FROM TASK WHERE type_id = 6, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false),
    (SELECT id FROM TASK WHERE type_id = 7, (SELECT id FROM PERSON WHERE email = 'john.doe@exsade.com'), false);