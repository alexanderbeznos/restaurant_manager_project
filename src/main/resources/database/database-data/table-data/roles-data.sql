--liquibase formatted sql

--changeSet abeznos:roles-data-01 logicalFilePath:database\database-data\tables
insert into roles (name) values ('admin'),
('user'),
('cook');