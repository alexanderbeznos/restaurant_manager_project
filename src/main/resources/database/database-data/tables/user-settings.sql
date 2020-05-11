--liquibase formatted sql

--changeSet abeznos:user-settings-01 logicalFilePath:database\database-data\tables
create table if not exists user_settings(
id serial primary key not null,
telephone varchar(50),
address varchar(100)
);