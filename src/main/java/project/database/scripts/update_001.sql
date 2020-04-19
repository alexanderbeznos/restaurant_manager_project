--liquibase formatted sql

--changeSet abeznos:update_001 logicalFilePath:project.database/scripts
create table if not exists roles(
id serial primary key,
name varchar(50)
);
create table if not exists user_settings(
id serial primary key not null,
telephone varchar(50),
address varchar(100)
);

create table if not exists users(
id serial primary key not null,
login varchar(50) unique not null,
password varchar(100) not null,
last_name varchar(50),
first_name varchar(50),
middle_name varchar(50),
user_settings_id integer references user_settings(id)
);

create table if not exists roles(
id serial primary key not null,
name varchar(50)
);

create table if not exists users_roles(
id serial primary key not null,
user_id integer references users(id),
roles_id integer references roles(id)
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