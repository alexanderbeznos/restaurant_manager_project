--liquibase formatted sql

--changeSet abeznos:tables-data-01 logicalFilePath:database\database-data\tables
insert into tables (number_of_table) values (4),
(7),
(9),
(11),
(13),
(15),
(19),
(20),
(24),
(29);