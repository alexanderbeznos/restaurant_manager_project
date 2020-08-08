--liquibase formatted sql

--changeSet abeznos:users-data-01 logicalFilePath:database\database-data\users-data
INSERT INTO users (login, password, last_name, first_name, middle_name, user_settings_id) values ('admin1', '$2y$12$azOcRW0FGP0bBLcIIOO80O9JmQxQ52WE..mYgMLrhseOWMvmZuNjK', 'Рябов' , 'Игорь' , 'Семёнович', 1),
('kitchen1', '$2y$12$sc/BzPkAV2AQj3da.l.YaON2BnnzPt77HF7/k3JiI6b/dpHb6NIqu', 'Козаков' , 'Михаил' , 'Викторович', 2),
('userTest1', '$2y$12$71T8GpbPiqzDbw0JZIDDE.EPAtMZBsjOXtS/S.9ccTEQPzm0N9mqK', 'Котова' , 'Ольга' , 'Борисовна', 3),
('userTest2', '$2y$12$4BBvj0/GxgYFfoD16IWhCuW0DlTTmYcvI.Ltmx8RcMT1xiQu73M.e', 'Борисов' , 'Илья' , 'Антонович', 4);
