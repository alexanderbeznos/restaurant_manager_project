--liquibase formatted sql

--changeSet abeznos:items-data-01 logicalFilePath:database\database-data\items-data
insert into items (order_food_id, dish_id, count, comment, done) values (1, 1, 5, 'Пожалуйста, острее сделайте', false),
(1, 12, 4,  'Без чеснока', false),
(2, 20, 1,  'Больше соли', false);