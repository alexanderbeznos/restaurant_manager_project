--liquibase formatted sql

--changeSet abeznos:order-food-01 logicalFilePath:database\database-data\order-food
create table if not exists order_food(
id serial primary key,
description varchar(500),
reserve_tables_id integer references reserve_tables(id),
user_id integer references users(id)
);

comment on table order_food is 'Заказ пользователя';

comment on column order_food.id is 'Уникальный ID заказа пользователя';

comment on column order_food.description is 'Описание заказа';

comment on column order_food.reserve_tables_id is 'ID заразервированного столика';

comment on column order_food.user_id is 'ID настроек пользователя';