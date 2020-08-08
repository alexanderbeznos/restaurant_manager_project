--liquibase formatted sql

--changeSet abeznos:reserve-tables-data-01 logicalFilePath:database\database-data\reserve-tables-data
INSERT INTO reserve_tables (start_time, finish_time, number_of_people, table_number, user_id, comment) values ('2020-10-01 10:10', '2020-10-01 12:10', 4, 1, 3, ''),
('2020-10-01 14:00', '2020-10-01 15:00', 2, 3, 3, ''),
('2020-10-01 18:00', '2020-10-01 19:15', 4, 5, 3, ''),
('2020-10-01 22:10', '2020-10-01 22:50', 2, 7, 3, ''),
('2020-10-01 09:00', '2020-10-01 10:30', 2, 10, 4, ''),
('2020-10-01 12:00', '2020-10-01 13:05', 2, 12, 4, ''),
('2020-10-01 15:00', '2020-10-01 16:00', 5, 15, 4, ''),
('2020-10-01 18:00', '2020-10-01 23:30', 12, 20, 4, '');
