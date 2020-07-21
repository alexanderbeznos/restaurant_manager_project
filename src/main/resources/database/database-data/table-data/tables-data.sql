--liquibase formatted sql

--changeSet abeznos:tables-data-01 logicalFilePath:database\database-data\tables-data
INSERT INTO tables (number_of_table, seats) values (1, 5),
(2, 4),
(3, 2),
(4, 4),
(5, 4),
(6, 6),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 6),
(12, 2),
(13, 4),
(14, 2),
(15, 5),
(16, 5),
(17, 5),
(18, 6),
(19, 10),
(20, 12);