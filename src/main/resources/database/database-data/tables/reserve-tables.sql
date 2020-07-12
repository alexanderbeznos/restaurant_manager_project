--liquibase formatted sql

--changeSet abeznos:reserve-tables-01 logicalFilePath:database\database-data\reserve-tables
create table if not exists reserve_tables(
id serial primary key,
start_time timestamp,
finish_time timestamp,
number_of_people integer,
table_number integer,
user_id integer
);

comment on table reserve_tables is 'Резервирование столиков';

comment on column reserve_tables.id is 'Уникальный ID резервирования столика';

comment on column reserve_tables.start_time is 'Начало';

comment on column reserve_tables.finish_time is 'Конец';

comment on column reserve_tables.number_of_people is 'Количество людей';

comment on column reserve_tables.table_number is 'Номер столика';

comment on column reserve_tables.user_id is 'ID пользователя';