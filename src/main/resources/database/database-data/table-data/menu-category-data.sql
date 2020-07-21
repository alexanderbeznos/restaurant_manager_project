--liquibase formatted sql

--changeSet abeznos:menu-category-data-01 logicalFilePath:database\database-data\menu-category-data
INSERT INTO menu_category (name) values ('Салаты'),
('Закуски'),
('Горячее'),
('Пицца'),
('Десерты'),
('Напитки');