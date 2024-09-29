create table if not exists `user`
(
    id char(19) not null primary key,
    name varchar(45),
    create_time datetime not null default  current_timestamp,
    update_time datetime not null default  current_timestamp on update current_timestamp

);
create table if not exists `address`
(
    id char(19) not null primary key,
    name varchar(45),
    user_id char(19),
    create_time datetime not null default  current_timestamp,
    update_time datetime not null default  current_timestamp on update current_timestamp,
    index (user_id)

);
explain
select * from address a where a.user_id=1283955919788601344;
explain
select * from address a join user u on a.user_id = u.id where a.id=1;
explain
select * from user u join address a on u.id = a.user_id where a.user_id=1283955919788601344