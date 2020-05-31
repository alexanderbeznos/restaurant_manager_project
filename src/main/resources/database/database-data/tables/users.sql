--liquibase formatted sql

--changeSet abeznos:users-01 logicalFilePath:database\database-data\users
create table if not exists users(
id serial primary key not null,
login varchar(50) unique not null,
password varchar(100) not null,
last_name varchar(50),
first_name varchar(50),
middle_name varchar(50),
user_settings_id integer references user_settings(id)
);

comment on table users is 'Данные пользователя';

comment on column users.id is 'Уникальный ID пользователя';

comment on column users.login is 'Логин';

comment on column users.password is 'Пароль';

comment on column users.last_name is 'Фамилия';

comment on column users.first_name is 'Имя';

comment on column users.middle_name is 'Отчество';

comment on column users.user_settings_id is 'ID настроек пользователя';

