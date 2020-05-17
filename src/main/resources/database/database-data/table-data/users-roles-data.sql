--liquibase formatted sql

--changeSet abeznos:users-roles-data-01 logicalFilePath:database\database-data\tables
insert into users_roles (user_id, roles_id) values (1, 1),
(1, 2),
(2, 2),
(3, 3);