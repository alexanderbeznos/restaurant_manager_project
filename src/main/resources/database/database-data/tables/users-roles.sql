--liquibase formatted sql

--changeSet abeznos:users-roles-01 logicalFilePath:database\database-data\tables
create table if not exists users_roles(
id serial primary key not null,
user_id integer references users(id),
roles_id integer references roles(id)
);