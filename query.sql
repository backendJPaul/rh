drop table if exists role;
create table role(
    id int auto_increment,
    name varchar(35),
    primary key(id)
);

insert into role(name)values("admin");
insert into role(name)values("saler");
insert into role(name)values("view");


drop table if exists user;
create table user(
    id int auto_increment,
    name varchar(35),
    password varchar(35),
    status boolean default true,
    idRole int,
    primary key(id),
    foreign key(idRole) references role(id)
);

drop procedure fetchUser;
delimiter //
create procedure fetchUser(
    in _status boolean
)
begin
    select user.id, user.name, user.password, role.name as role, user.status from user inner join role on user.idRole = role.id and status = _status;
end //

drop procedure gotoUserId;
delimiter //
create procedure gotoUserId(
    in _status boolean,
    in _id int
)
begin
    select user.id, user.name, user.password, role.name as role, user.status from user inner join role on user.idRole = role.id where user.id = _id and user.status = _status;
end //

drop procedure if exists saveUser;
delimiter //
create procedure saveUser(
    in _name varchar(35),
    in _password varchar(35),
    in _idRole int
)
begin
    insert into user(name,password,idRole)values(_name,_password,_idRole);
    call gotoUserId(true,LAST_INSERT_ID());
end //

call saveUser("admin","root",1);
call saveUser("mary","root",2);
call saveUser("anabel","root",2);
call saveUser("estrella","root",2);
call saveUser("heidi","root",2);


drop procedure if exists deleteUser;
delimiter //
create procedure deleteUser(
    in _id int
)
begin
    update user set user.status = false where user.id = _id;
    call gotoUserId(false,_id);
end //

drop procedure if exists searchUser;
create procedure searchUser(
    in _pattern varchar(35)
)
begin
    select user.id, user.name, user.password, role.name as role from user inner join role on user.idRole = role.id where user.name like concat('%',_pattern,'%') and user.status = true;

end //


drop procedure if exists updateUser;
delimiter //
create procedure updateUser(
    in _id int,
    in _name varchar(35),
    in _password varchar(35),
    in _idRole int
)
begin
    update user set user.name = _name, user.password = _password, user.idRole = _idRole where user.id = _id;
    call gotoUserId(true,_id);

end//


call fetchUser(true);
call updateUser(7,"roxana","root",3);