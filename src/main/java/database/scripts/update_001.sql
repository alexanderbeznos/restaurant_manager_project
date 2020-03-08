--liquibase formatted sql

--changeSet abeznos:update_001 logicalFilePath:database/scripts
create table if not exists user_settings(
id serial primary key,
telephone varchar(50),
address varchar(100)
);

create table if not exists users(
id serial primary key,
login varchar(50) unique,
password varchar(100),
last_name varchar(50),
first_name varchar(50),
middle_name varchar(50),
user_settings_id integer references user_settings(id)
);

create table if not exists tables(
id serial primary key,
number_of_table int
);

create table if not exists reserve_tables(
id serial primary key,
start_time varchar(50),
finish_time varchar(50),
tables_id integer references tables(id),
user_id integer references users(id)
);

create table if not exists order_food(
id serial primary key,
description varchar(500),
reserve_tables_id integer references reserve_tables(id),
user_id integer references users(id)
);