--liquibase formatted sql

--changeSet abeznos:items-data-01 logicalFilePath:database\database-data\items-data
INSERT INTO items (order_food_id, dish_id, count, serving_time, comment, take) values (1, 1, 5, null,'Пожалуйста, острее сделайте', null),
(1, 12, 4, null, 'Без чеснока', null),
(2, 20, 1, null, 'Больше соли', null);