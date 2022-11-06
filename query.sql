drop table if exists role;
create table role(
    id int auto_increment,
    name varchar(35),
    primary key(id)
);
drop table if exists user;
create table user (
    id int auto_increment,
    name varchar(35),
    password varchar(35),
    idRole int,
    status boolean default true,
    primary key(id),
    foreign key(idRole) references role(id)
);

insert into role(name)values("admin");
insert into role(name)values("saler");
insert into role(name)values("view");

drop procedure if exists fetchUser;
delimiter //
create procedure fetchUser(
    in tf boolean
)
begin
    select user.id, user.name, user.password, role.name as role from user inner join role on user.idRole = role.id where user.status = tf;
end //

drop procedure if exists gotoIdUser;
delimiter //
create procedure gotoIdUser(
    in _id int,
    in _tf boolean
)
begin
    select user.id, user.name, user.password, role.name as role from user inner join role on user.idRole = role.id where user.id = _id and user.status = _tf;
end //

call fetchUser(true);

drop procedure if exists saveUser;
delimiter //
create procedure saveUser(
   in _name varchar(35),
    in _password varchar(35),
    in _idRole int
)
begin
    insert into user(name,password,idRole)values(_name,_password,_idRole);
    call gotoIdUser(LAST_INSERT_ID(),true);
end //

call saveUser("admin","root",2);
call saveUser("cesia","root",2);
call saveUser("mary","root",2);
call saveUser("anabel","root",2);
call saveUser("estrella","root",2);
call saveUser("heidi","root",2);

drop procedure if exists updateUser;
delimiter //
create procedure updateUser(
    in _id int,
    in _name varchar(35),
    in _password varchar(35),
    in _idRole int
)
begin
    update user set user.name = _name,
                    user.password = _password,
                    user.idRole = _idRole where user.id = _id and user.status = 1;
    call gotoIdUser(_id,true);
end //


drop procedure if exists deleteUser;
delimiter //
create procedure deleteUser(
    in _id int
)
begin
    update user set user.status = false where user.id = _id;
    call gotoIdUser(_id,false);
end //

drop procedure if exists searchUser;
delimiter //
create procedure searchUser(
    in pattern varchar(35)
)
begin
    select user.id, user.name, role.name as role from user
    inner join role on user.idRole = role.id
    where user.name like concat("%",pattern,"%") and user.status = true;
end //
