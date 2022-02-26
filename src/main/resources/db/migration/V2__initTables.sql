# create table Role (roleId integer primary key, role varchar(20));
#
# insert into Role values(1,'Project Manager');
# insert into Role values(1,'Employee');

create table User (
    uId integer primary key,
    username varchar(20) unique,
    email varchar(30),
    password varchar(50),
    address varchar(150),
    phoneNo char(10),
    dob date,
    roles varchar(50)
);

# insert into User values (1,'user', 'user@mail.com', '123456', 'pune', '9087654321','1998-06-21','Project Manager')

# create table UserRole (roleId integer references Role(roleId), uID integer references User(uId));