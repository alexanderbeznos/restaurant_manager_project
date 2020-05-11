--liquibase formatted sql

--changeSet abeznos:users-data-01 logicalFilePath:database\database-data\tables
insert into users (login, password, last_name, first_name, middle_name, user_settings_id) values ('q', '$2y$12$pEI5tC.7Q7a.lG.g9dO.4.lG/Huon0S26MYJiYsp3ZKK3bmtHpamS.PqV4hWSiVhnK4DDnMzvwo2KG', 'Гошел' , 'Игорь' , 'Семёнович', 1),
('a', '$2y$12$gDK9cfHKMShT7jsq6OlTvedWJr8i6SC1maOYpPbPNGo6SOciA6jHC', 'Лорина' , 'Ольга' , 'Борисовна', 2),
('z', '$2y$12$Ro2kJUada0MNHbgQFr/r1eRA.Ug9fmrT.UL1EzjF30qgVEgh5jLMO', 'Доров' , 'Михаил' , 'Викторович', 3);