--liquibase formatted sql

--changeSet abeznos:roles-01 logicalFilePath:database\database-data\roles
create table if not exists roles(
id serial primary key not null,
name varchar(50)
);

comment on table roles is 'Роли';

comment on column roles.id is 'Уникальный ID роли';

comment on column roles.name is 'Название';
