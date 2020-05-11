--liquibase formatted sql

--changeSet abeznos:order-food-data-01 logicalFilePath:database\database-data\tables
insert into order_food (description, reserve_tables_id, user_id) values ('Борщ, пюре, котлета, оливье', 1, 1),
('Суп, гречка, рыба, овощной салат', 2, 2),
('Щи, плов, мясо, греческий салат, чай', 3, 3);