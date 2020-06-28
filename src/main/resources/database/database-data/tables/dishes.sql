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
without_gluten boolean,
star_one integer,
star_two integer,
star_three integer,
star_four integer,
star_five integer
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

comment on column dishes.star_one is 'Оценка блюда на 1';

comment on column dishes.star_two is 'Оценка блюда на 2';

comment on column dishes.star_three is 'Оценка блюда на 3';

comment on column dishes.star_four is 'Оценка блюда на 4';

comment on column dishes.star_five is 'Оценка блюда на 5';


