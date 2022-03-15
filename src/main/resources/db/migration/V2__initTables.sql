

create table User (
    u_id integer primary key,
    username varchar(20) unique,
    email varchar(30),
    password varchar(100),
    address varchar(150),
    phone_no char(10),
    dob date,
    roles varchar(50),
    qualification varchar(255),
    verification_code varchar(6),
    otp_verified boolean
);

insert into User values (1,'shreya', 'shreyaramtirth4@gmail.com', '$2a$04$3AFJH1m1VGEUbSiumjmXrORx2BGpX.4fAyMs0DJvmEZ.koD38XrIu',
                         'pune',
                         '9087654321','1998-06-21',
                         'Project Manager','Front-End Developer',null,
                         false);

create table Project (
    id bigint unsigned primary key,
    project_name varchar(50) unique

);
