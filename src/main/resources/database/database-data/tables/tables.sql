--liquibase formatted sql

--changeSet abeznos:tables-01 logicalFilePath:database\database-data\tables
create table if not exists tables(
id serial primary key,
number_of_table int,
seats int
);

comment on table tables is 'Столики';

comment on column tables.id is 'Уникальный ID столика';

comment on column tables.number_of_table is 'Номер столика';

comment on column tables.seats is 'Количество мест столика';