--liquibase formatted sql

--changeSet abeznos:reserve-tables-01 logicalFilePath:database\database-data\tables
create table if not exists reserve_tables(
id serial primary key,
start_time varchar(50),
finish_time varchar(50),
tables_id integer references tables(id),
user_id integer references users(id)
);