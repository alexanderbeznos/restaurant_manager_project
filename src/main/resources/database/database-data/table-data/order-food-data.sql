--liquibase formatted sql

--changeSet abeznos:order-food-data-01 logicalFilePath:database\database-data\order-food-data
insert into order_food (user_id, name_of_user, address, phone, description) values (1, '', '', '', 'Свечи зажгите красиво'),
(1, '', '', '', 'Красные салфетки, пожалуйста'),
(2, '', '', '', 'Цветы какие-то на столе, пожалуйста');