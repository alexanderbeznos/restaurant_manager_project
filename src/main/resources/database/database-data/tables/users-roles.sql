--liquibase formatted sql

--changeSet abeznos:users-roles-01 logicalFilePath:database\database-data\users-roles
create table if not exists users_roles(
id serial primary key not null,
user_id integer references users(id),
roles_id integer references roles(id)
);

comment on table users_roles is 'Заказ пользователя';

comment on column users_roles.id is 'Уникальный ID связи пользователя с ролью';

comment on column users_roles.user_id is 'ID пользователя';

comment on column users_roles.roles_id is 'ID роли';