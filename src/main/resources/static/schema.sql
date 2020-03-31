CREATE TABLE IF NOT EXISTS volunteer
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    volunteer_name  VARCHAR (20)        NOT NULL,
    surname         VARCHAR (20)        NOT NULL,
    email           VARCHAR (30) UNIQUE NOT NULL,
    password        VARCHAR (30)        NOT NULL,
    phone           VARCHAR (11) UNIQUE NOT NULL,
    date_of_birth   DATE                NOT NULL,
    trust_level     VARCHAR (32)        NOT NULL,
    is_blocked      BOOLEAN             DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS category
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    category_name       VARCHAR (50) UNIQUE NOT NULL,
    description         VARCHAR (128) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS task
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    task_name       VARCHAR (50)       NOT NULL,
    description     VARCHAR (255)      NOT NULL,
    creation_date   DATE               NOT NULL,
    title           VARCHAR (30)       NOT NULL,
    is_active       BOOLEAN            DEFAULT FALSE,
    participants    INT                NOT NULL,
    status          VARCHAR (30)       NOT NULL,
    priority        VARCHAR (30)       NOT NULL,
    category_id     INT                NOT NULL,

    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
            REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS volunteer_task
(
    volunteer_id        INT NOT NULL,
    task_id             INT NOT NULL,
    is_creator          BOOLEAN DEFAULT FALSE,
    participation_date  DATE NOT NULL,
    comment             VARCHAR (50) NOT NULL,
    approved            BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (volunteer_id, task_id),

    CONSTRAINT fk_volunteer_volunteer_task
        FOREIGN KEY (volunteer_id)
            REFERENCES volunteer (id),

    CONSTRAINT fk_task_volunteer_task
        FOREIGN KEY (task_id)
            REFERENCES task (id)
);

INSERT INTO category (id, category_name, description)
VALUES (1, 'Disabled', 'Helping disabled people'),
       (2, 'Elderly', 'Helping elderly'),
       (3, 'Fundraising', 'Raising funds');

INSERT INTO volunteer(id,volunteer_name, surname, email, password, phone, date_of_birth, trust_level, is_blocked)
VALUES (1,'Nazar', 'Koval', 'marmeladka228@gmail.com', 'Kebab1488', '0679359820', '2001-01-20', 'NOVICE', false),
       (2,'Yura', 'Khanas', 'yura1@gmail.com', 'Kaban228', '0990095274', '2000-12-17', 'NOVICE', true);

INSERT INTO task(id, task_name, description, creation_date, title, is_active, participants, status, priority, category_id)
VALUES (1, 'Task 1', 'Task number 1', CURRENT_DATE(), 'Title 1', true, 10, 'ACTIVE', 'HIGH', 1),
       (2,'Task 2', 'Task number 2', '2020-03-27', 'Title 2', false, 20, 'ACTIVE', 'LOW', 3);

INSERT INTO volunteer_task (volunteer_id, task_id, is_creator, participation_date, comment, approved)
VALUES (1, 1, true, CURRENT_DATE(), 'first user - first task', true ),
       (1, 2, false, '2020-07-27', 'first user - second task', false ),
       (2, 2, true, '2000-03-03', 'second user - second task', true );