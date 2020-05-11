--liquibase formatted sql

--changeSet abeznos:roles-01 logicalFilePath:database\database-data\tables
create table if not exists roles(
id serial primary key not null,
name varchar(50)
);