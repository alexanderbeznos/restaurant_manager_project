--liquibase formatted sql

--changeSet abeznos:user-settings-01 logicalFilePath:database\database-data\user-settings
create table if not exists user_settings(
id serial primary key not null,
telephone varchar(50),
address varchar(100)
);

comment on table user_settings is 'Настройки пользователя';

comment on column user_settings.id is 'Уникальный ID настроек пользователя';

comment on column user_settings.telephone is 'Телефон';

comment on column user_settings.address is 'Адрес';