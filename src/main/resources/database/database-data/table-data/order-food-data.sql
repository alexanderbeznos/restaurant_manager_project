--liquibase formatted sql

--changeSet abeznos:order-food-data-01 logicalFilePath:database\database-data\order-food-data
INSERT INTO order_food (order_type, user_id, reserved_table_id, name_of_user, address, phone, description) values ('D', 3, null, '', '', '', 'Приборы на троих'),
('D', 3, null, '', '', '', ''),
('D', 4, null, '', '', '', ''),
('R', 1, 1, '', '', '', 'Цветы какие-то на столе, пожалуйста'),
('R', 2, 2, '', '', '', 'Красные салфетки, пожалуйста'),
('R', 3, 3, '', '', '', ''),
('R', 3, 4, '', '', '', ''),
('R', 3, 5, '', '', '', 'Чтобы свечи были'),
('R', 3, 6, '', '', '', ''),
('R', 3, 7, '', '', '', ''),
('R', 3, 8, '', '', '', '');