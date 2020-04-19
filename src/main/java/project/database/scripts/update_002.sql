--liquibase formatted sql

--changeSet abeznos:update_002 logicalFilePath:project.database/scripts
insert into roles (name) values ('admin'),
('user'),
('cook');
insert into user_settings (telephone, address) values ('+79182504748', 'г.Москва, ул.Весенная, д.2, кв.40'),
('+79253528795', 'г.Москва, ул.Лопырева, д.41, кв.87'),
('+79184715896', 'г.Казань, ул.Нероваляева, д.24, кв.24');
insert into users (login, password, last_name, first_name, middle_name, user_settings_id) values ('q', 'wwwwwwww', 'Гошел' , 'Игорь' , 'Семёнович', 1),
('a', 'ssssssss', 'Лорина' , 'Ольга' , 'Борисовна', 2),
('z', 'xxxxxxxx', 'Доров' , 'Михаил' , 'Викторович', 3);
insert into users_roles (user_id, roles_id) values (1, 1),
(1, 2),
(1, 3),
(2, 2),
(3, 3);
insert into tables (number_of_table) values (4),
(7),
(9),
(11),
(13),
(15),
(19),
(20),
(24),
(29);
insert into reserve_tables (start_time, finish_time, tables_id, user_id) values ('15:30', '17:00', 4, 1),
('17:00', '18:00', 4, 2),
('18:00', '19:15', 4, 3);
insert into order_food (description, reserve_tables_id, user_id) values ('Борщ, пюре, котлета, оливье', 1, 1),
('Суп, гречка, рыба, овощной салат', 2, 2),
('Щи, плов, мясо, греческий салат, чай', 3, 3);
