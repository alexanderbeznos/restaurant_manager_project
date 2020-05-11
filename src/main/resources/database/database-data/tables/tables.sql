--liquibase formatted sql

--changeSet abeznos:tables-01 logicalFilePath:database\database-data\tables
create table if not exists tables(
id serial primary key,
number_of_table int
);
