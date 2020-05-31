--liquibase formatted sql

--changeSet abeznos:users-data-01 logicalFilePath:database\database-data\users-data
insert into users (login, password, last_name, first_name, middle_name, user_settings_id) values ('q', '$2y$12$Acshvrl6MTr7vmY.YftMde2Rpzl3S/8472LAFikLNTyMuMwlSyCLi', 'Гошел' , 'Игорь' , 'Семёнович', 1),
('a', '$2y$12$FpHnmRYN00lU2zkk.1VuQ.TS9xhQrGp7Z0sUnc66bD9k959kAHgDa', 'Лорина' , 'Ольга' , 'Борисовна', 2),
('z', '$2y$12$6A.eMC9KuTHslAEW0cLjS.fPmTMNUE5XhR.axaCcRcen0mSoYECGy', 'Доров' , 'Михаил' , 'Викторович', 3);