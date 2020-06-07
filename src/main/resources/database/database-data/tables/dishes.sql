--liquibase formatted sql

--changeSet abeznos:dishes-01 logicalFilePath:database\database-data\dishes
create table if not exists dishes(

id serial primary key not null,
name varchar(50),
menu_category_id integer references menu_category(id),
description varchar(400),
ingredients varchar(200),
photo varchar(50),
price decimal,
calories varchar(50),
spicy boolean,
for_vegans boolean,
without_sugar boolean,
without_gluten boolean
);

comment on table dishes is 'Блюда';

comment on column dishes.id is 'Уникальный ID блюда';

comment on column dishes.name is 'Название блюда';

comment on column dishes.description is 'Описание';

comment on column dishes.ingredients is 'Ингредиенты';

comment on column dishes.photo is 'Путь к фотографии';

comment on column dishes.price is 'Цена';

comment on column dishes.calories is 'Калории на 100 грамм';

comment on column dishes.spicy is 'Острое';

comment on column dishes.for_vegans is 'Для веганов';

comment on column dishes.without_sugar is 'Без сахара';

comment on column dishes.without_gluten is 'Без глютена';


