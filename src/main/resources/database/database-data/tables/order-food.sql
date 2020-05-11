--liquibase formatted sql

--changeSet abeznos:order-food-01 logicalFilePath:database\database-data\tables
create table if not exists order_food(
id serial primary key,
description varchar(500),
reserve_tables_id integer references reserve_tables(id),
user_id integer references users(id)
);