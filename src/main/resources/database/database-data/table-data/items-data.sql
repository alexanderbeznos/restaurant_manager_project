--liquibase formatted sql

--changeSet abeznos:items-data-01 logicalFilePath:database\database-data\items-data
INSERT INTO items (order_food_id, dish_id, count, serving_time, comment, take) values (1, 1, 5, null,'Пожалуйста, острее сделайте', null),
(1, 15, 4, null, '', null),
(2, 4, 3, null, 'Больше соли', null),
(3, 19, 5, null, '', null),
(4, 17, 2, '2020-11-01 10:15', 'Острее и солёнее', null),
(4, 37, 2, '2020-11-01 11:00', '', null),
(5, 24, 2, null, '', null),
(6, 3, 4, null, '', null),
(7, 13, 2, '2020-11-01 22:10', '', null),
(7, 18, 3, '2020-11-01 22:30', '', null),
(8, 14, 4, null, '', null),
(8, 32, 2, null, '', null),
(9, 26, 3, null, '', null),
(10, 40, 4, '2020-11-01 15:15', '', null),
(11, 27, 10, '2020-11-01 18:15', '', null),
(11, 18, 15, '2020-11-01 19:00', '', null),
(11, 4, 20, '2020-11-01 21:30', '', null),
(11, 42, 20, '2020-11-01 20:15', '', null),
(11, 9, 15, null, '', null);

