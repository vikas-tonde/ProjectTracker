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
        'Project Manager', 'Front-End Developer', null,
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
values ('Build', 39);
insert into phase(phase_name, percentage)
values ('Test', 25);
insert into phase(phase_name, percentage)
values ('Implementation', 5);


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
    c_id         bigint unsigned references client (c_id)
);

/*create table technology (
  t_id bigint unsigned AUTO_INCREMENT primary key,
  technology_name varchar(30) unique
);

insert into technology(technology_name) values ("Java");
insert into technology(technology_name) values ("React");
insert into technology(technology_name) values ("Next js");
insert into technology(technology_name) values ("MySQL");
insert into technology(technology_name) values ("Angular");
insert into technology(technology_name) values ("Spring");
insert into technology(technology_name) values ("WordPress");
insert into technology(technology_name) values ("Node js");
insert into technology(technology_name) values ("Redux");
insert into technology(technology_name) values ("MongoDB");
insert into technology(technology_name) values ("Android");
insert into technology(technology_name) values ("Flutter");
insert into technology(technology_name) values ("IOS");


create table project_technology(
    p_id bigint unsigned references project(p_id),
    t_id bigint unsigned references technology(t_id),
    primary key (p_id, t_id)
);*/


insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('Random Project', '2022-3-19', '2022-9-09', 5000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '80', 'java,HTML,React');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('Non Random Project', '2022-3-19', '2022-9-09', 5000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '60', 'Wordpress');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('very Random Project', '2022-3-19', '2022-9-09', 5000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '70', 'Mongodb');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('So much Random Project', '2022-3-19', '2022-9-09', 5000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '20', 'spring');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('Significant', '2022-3-19', '2022-9-09', 5000, null,
        'This is Significant project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '50', 'Node JS');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('The website of websites', '2022-3-19', '2022-9-09', 5000, null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '69', 'Android');

insert into project (title, date_added, deadline, cost, priority, description, progress, technologies)
values ('A project of final year student', '2022-3-19', '2022-9-09', 5000, null,
        'This is not much random as others This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '45', 'Flutter');


/*insert into project_technology values (1,2);
insert into project_technology values (1,8);
insert into project_technology values (1,10);
insert into project_technology values (2,5);
insert into project_technology values (2,7);
insert into project_technology values (3,9);
insert into project_technology values (3,12);
insert into project_technology values (4,3);
insert into project_technology values (4,4);
insert into project_technology values (5,4);
insert into project_technology values (5,7);
insert into project_technology values (6,2);
insert into project_technology values (6,9);
insert into project_technology values (7,10);
insert into project_technology values (7,11);*/


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



create table client
(
    c_id           bigint unsigned auto_increment primary key,
    client_name    varchar(50) unique,
    representative varchar(50),
    phone_no       char(10),
    email          varchar(30),
    address        varchar(150)
);
