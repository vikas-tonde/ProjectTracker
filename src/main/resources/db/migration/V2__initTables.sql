

create table User (
    u_id integer primary key,
    username varchar(20) unique,
    email varchar(30),
    password varchar(50),
    address varchar(150),
    phone_no char(10),
    dob date,
    roles varchar(50),
    verification_code varchar(6)

);

insert into User values (1,'shreya', 'shreyaramtirth4@gmail.com', 'shreya123', 'pune', '9087654321','1998-06-21','Project Manager',null)
