--liquibase formatted sql

--changeSet abeznos:users-01 logicalFilePath:database\database-data\tables
create table if not exists users(
id serial primary key not null,
login varchar(50) unique not null,
password varchar(100) not null,
last_name varchar(50),
first_name varchar(50),
middle_name varchar(50),
user_settings_id integer references user_settings(id)
);