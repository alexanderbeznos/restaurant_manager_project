--liquibase formatted sql

--changeSet abeznos:order-food-01 logicalFilePath:database\database-data\order-food
create table if not exists order_food(
id serial primary key,
order_type varchar,
user_id integer,
reserved_table_id integer,
name_of_user varchar,
address varchar,
phone varchar,
description varchar
);

comment on table order_food is 'Заказ пользователя';

comment on column order_food.id is 'Уникальный ID заказа пользователя';

comment on column order_food.order_type is 'Тип заказа';

comment on column order_food.user_id is 'ID пользователя(зарегистрированного) сделавшего заказ';

comment on column order_food.reserved_table_id is 'ID резервирования';

comment on column order_food.name_of_user is 'имя пользователя(не зарегистрированного) сделавшего заказ';

comment on column order_food.address is 'адрес пользователя(не зарегистрированного) сделавшего заказ';

comment on column order_food.phone is 'телефон пользователя(не зарегистрированного) сделавшего заказ';

comment on column order_food.description is 'Описание заказа';


