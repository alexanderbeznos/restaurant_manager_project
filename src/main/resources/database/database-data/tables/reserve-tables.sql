--liquibase formatted sql

--changeSet abeznos:reserve-tables-01 logicalFilePath:database\database-data\tables
create table if not exists reserve_tables(
id serial primary key,
start_time varchar(50),
finish_time varchar(50),
tables_id integer references tables(id),
user_id integer references users(id)
);

comment on table reserve_tables is 'Резервирование столиков';

comment on column reserve_tables.id is 'Уникальный ID резервирования столика';

comment on column reserve_tables.start_time is 'Начало';

comment on column reserve_tables.finish_time is 'Конец';

comment on column reserve_tables.tables_id is 'ID столика';

comment on column reserve_tables.user_id is 'ID пользователя';