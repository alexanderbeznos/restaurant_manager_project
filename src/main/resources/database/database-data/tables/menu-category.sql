--liquibase formatted sql

--changeSet abeznos:menu-category-01 logicalFilePath:database\database-data\menu-category
create table if not exists menu_category(
id serial primary key,
name varchar(50)
);

comment on table menu_category is 'Категории блюд';

comment on column menu_category.id is 'Уникальный ID категории блюда';

comment on column menu_category.name is 'Категория';