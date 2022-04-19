create table user
(
    u_id              bigint unsigned auto_increment primary key,
    first_name        varchar(50),
    last_name         varchar(50),
    username          varchar(20) unique,
    email             varchar(30) unique,
    password          varchar(100),
    address           varchar(150),
    phone_no          char(10),
    dob               date,
    roles             varchar(50),
    qualification     varchar(255),
    verification_code varchar(6),
    otp_verified      boolean
);

insert into user
values (1, 'Shreya',
        'Ramtirth',
        'shreya',
        'shreyaramtirth4@gmail.com',
        '$2a$04$3AFJH1m1VGEUbSiumjmXrORx2BGpX.4fAyMs0DJvmEZ.koD38XrIu',
        'pune',
        '8975080981', '2000-10-24',
        'Employee', 'Front-End Developer', null,
        false);

insert into user
values (2, 'Vikas',
        'Tonde',
        'vikasT',
        'tondev23@gmail.com',
        '$2a$12$gEC6AzuoZ6lL9JbdZNKl7.wuVXHsklhTDzq.SlFvUzLw6m0Nw0bQ.',
        'Pune',
        '8308859743', '1998-07-21',
        'Project Managewr', 'Full Stack Developer', null,
        false);

create table phase
(
    phase_id   bigint unsigned auto_increment primary key,
    phase_name varchar(30) unique,
    percentage int
);

insert into phase(phase_name, percentage)
values ('Initiation', 9);
insert into phase(phase_name, percentage)
values ('Definition', 9);
insert into phase(phase_name, percentage)
values ('Design', 13);
insert into phase(phase_name, percentage)
values ('Development', 39);
insert into phase(phase_name, percentage)
values ('Testing', 25);
insert into phase(phase_name, percentage)
values ('Deployment', 5);
insert into phase(phase_name, percentage)
values ('Completed', 0);


create table project
(
    p_id         bigint unsigned AUTO_INCREMENT primary key,
    title        varchar(50) unique,
    date_added   date,
    deadline     date,
    cost         decimal(15, 0),
    priority     varchar(9),
    description  text,
    progress     varchar(10),
    technologies text,
    c_id         bigint unsigned references client (c_id),
    phase_id     bigint unsigned references phase (phase_id)
);

create table client
(
    c_id           bigint unsigned auto_increment primary key,
    client_name    varchar(50) unique,
    representative varchar(50),
    phone_no       char(10),
    email          varchar(30),
    address        varchar(150)
);

insert into client(client_name, representative, phone_no, email, address)
values ('Amazon', 'jeff bezos', '9607854321', 'jeff@gmail.com', 'Pune Maharashtra');
insert into client(client_name, representative, phone_no, email, address)
values ('Microsoft', 'Bill Gates', '9607854321', 'bill@gmail.com', 'Pune Maharashtra');
insert into client(client_name, representative, phone_no, email, address)
values ('Meta', 'Mark Zckerberg', '9607854321', 'mark@gmail.com', 'Pune Maharashtra');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies, c_id, phase_id)
values ('Project Avengers', '2022-3-19', '2022-9-09', 5000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '80', 'java,HTML,React', 1, 1);

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies, c_id, phase_id)
values ('Project Ragnarok', '2022-3-19', '2022-9-09', 4000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '60', 'Wordpress', 1, 1);

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies,c_id,phase_id)
values ('Project Ultron', '2022-3-19', '2022-9-09', 6000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '70', 'Mongodb',2,1);

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies,c_id,phase_id)
values ('Project VR', '2022-3-19', '2022-9-09', 7000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '20', 'spring',3,1);

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies,c_id,phase_id)
values ('Project Hail Hydra', '2022-3-19', '2022-9-09', 12000, null,
        'This is Significant project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '50', 'Node JS',2,1);

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies,c_id,phase_id)
values ('Just Do It', '2022-3-19', '2022-9-09', 10000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '69', 'Android',1,1);

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies,c_id,phase_id)
values ('Cross Platform App', '2022-3-19', '2022-9-09', 8000, null,
        'This is not much random as others This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '45', 'Flutter',2,1);


create table task
(
    task_id      bigint unsigned auto_increment primary key,
    p_id         bigint unsigned references project (p_id),
    u_id         bigint unsigned references user (u_id),
    task         varchar(50),
    assigned_on  DATE,
    completed_on date,
    completed    boolean
);

insert into task (p_id, u_id, task, assigned_on, completed)
values (1, 1, 'Work on project', '2022-03-21', false);

insert into task (p_id, u_id, task, assigned_on, completed)
values (1, 1, 'Work on project2', '2022-03-21', false);

insert into task (p_id, u_id, task, assigned_on, completed)
values (2, 2, 'Work on Project', '2022-03-21', false);




