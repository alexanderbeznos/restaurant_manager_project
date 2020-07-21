--liquibase formatted sql

--changeSet abeznos:order-food-data-01 logicalFilePath:database\database-data\order-food-data
INSERT INTO order_food (order_type, user_id, reserved_table_id, name_of_user, address, phone, description) values ('D', 1, null, '', '', '', 'Приборы на троих'),
('D', 1, null, '', '', '', ''),
('D', 2, null, '', '', '', ''),
('R', 1, 7, '', '', '', 'Цветы какие-то на столе, пожалуйста'),
('R', 2, 8, '', '', '', 'Красные салфетки, пожалуйста'),
('R', 3, 9, '', '', '', 'Чтобы свечи были');