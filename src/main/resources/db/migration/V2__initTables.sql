create table User (
    u_id bigint unsigned auto_increment primary key,
    first_name varchar(50),
    last_name varchar(50),
    username varchar(20) unique,
    email varchar(30) unique,
    password varchar(100),
    address varchar(150),
    phone_no char(10),
    dob date,
    roles varchar(50),
    qualification varchar(255),
    verification_code varchar(6),
    otp_verified boolean
);

insert into user values (1,'Shreya',
                         'Ramtirth',
                         'shreya',
                         'shreyaramtirth4@gmail.com',
                         '$2a$04$3AFJH1m1VGEUbSiumjmXrORx2BGpX.4fAyMs0DJvmEZ.koD38XrIu',
                         'pune',
                         '9087654321','1998-06-21',
                         'Project Manager','Front-End Developer',null,
                         false
                        );

create table project (
    p_id bigint unsigned AUTO_INCREMENT primary key,
    title varchar(50) unique,
    date_added date,
    deadline date,
    cost decimal(15,0),
    priority varchar(9),
    description text,
    progress varchar(10)
);

create table tag(
    tag_id bigint unsigned auto_increment primary key,
    tag_name varchar(20) unique
);

insert into tag(tag_name) values ('SCM');
insert into tag(tag_name) values ('CRM');
insert into tag(tag_name) values ('ERP');


create table project_tag(
    p_id bigint unsigned references project(p_id),
    tag_id bigint unsigned references tag(tag_id),
    primary key (p_id, tag_id)
);

insert into project (title, date_added, deadline, cost, priority, description, progress)
values ('Random Project', '2022-3-19', '2022-9-09', 5000,null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '80');

insert into project (title, date_added, deadline, cost, priority, description, progress)
values ('Non Random Project', '2022-3-19', '2022-9-09', 5000,null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '60');

insert into project (title, date_added, deadline, cost, priority, description, progress)
values ('very Random Project', '2022-3-19', '2022-9-09', 5000,null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '70');

insert into project (title, date_added, deadline, cost, priority, description, progress)
values ('So much Random Project', '2022-3-19', '2022-9-09', 5000,null,
        'This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project This is random project',
        '20');

insert into project_tag(p_id, tag_id) VALUES (1,2);
insert into project_tag(p_id, tag_id) VALUES (2,1);
insert into project_tag(p_id, tag_id) VALUES (3,3);
insert into project_tag(p_id, tag_id) VALUES (4,1);


create table task(
    task_id bigint unsigned auto_increment primary key,
    p_id bigint unsigned references project(p_id),
    u_id bigint unsigned references  user(u_id),
    task varchar(50),
    assigned_on DATE,
    completed_on date
);


create table client (
    c_id bigint unsigned auto_increment primary key,
    client_name varchar(50),
    representative varchar(50),
    phone_no char(10),
    email varchar(30),
    address varchar(150)
);
