--liquibase formatted sql

--changeSet abeznos:reserve-tables-data-01 logicalFilePath:database\database-data\reserve-tables-data
insert into reserve_tables (start_time, finish_time, number_of_people, table_number, user_id) values ('2020-07-20 10:10', '2020-07-20 12:10', 4, 1, 1),
('2020-07-20 18:10', '2020-07-20 18:50', 4, 1, 3),
('2020-07-20 13:30', '2020-07-20 15:00', 4, 1, 2),
('2020-07-20 12:11', '2020-07-20 12:30', 4, 1, 2),
('2020-07-20 12:30', '2020-07-20 13:20', 4, 1, 2),
('2020-07-20 15:10', '2020-07-20 16:00', 4, 1, 2),
('2020-07-20 09:10', '2020-07-20 10:00', 3, 2, 1),
('2020-07-20 11:00', '2020-07-20 13:00', 3, 2, 1),
('2020-07-20 15:30', '2020-07-20 16:30', 3, 2, 1);
