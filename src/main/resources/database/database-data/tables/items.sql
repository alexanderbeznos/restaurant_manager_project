--liquibase formatted sql

--changeSet abeznos:items-01 logicalFilePath:database\database-data\items
create table if not exists items(
id serial primary key,
order_food_id integer,
dish_id integer references dishes(id),
count integer,
serving_time timestamp,
comment varchar,
done boolean default false
);

comment on table menu_category is 'Заказ блюда пользователем';

comment on column items.id is 'Уникальный ID заказа блюда пользователем';

comment on column items.order_food_id is 'Относится к номеру заказа';

comment on column items.dish_id is 'Уникальный ID блюда';

comment on column items.count is 'Количество заказыннх блюд';

comment on column items.serving_time is 'Время подачи блюда(в ресторане)';

comment on column items.comment is 'Комментирий к заказу блюда';

comment on column items.done is 'Заказ готов';
