--liquibase formatted sql

--changeSet abeznos:reserve-tables-data-01 logicalFilePath:database\database-data\tables
insert into reserve_tables (start_time, finish_time, tables_id, user_id) values ('15:30', '17:00', 4, 1),
('17:00', '18:00', 4, 2),
('18:00', '19:15', 4, 3);