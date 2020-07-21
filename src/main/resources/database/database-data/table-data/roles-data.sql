--liquibase formatted sql

--changeSet abeznos:roles-data-01 logicalFilePath:database\database-data\roles-data
INSERT INTO roles (name) values ('ADMIN'),
('USER'),
('COOK');