--liquibase formatted sql

--changeSet abeznos:user-settings-data-01 logicalFilePath:database\database-data\user-settings-data
insert into user_settings (telephone, address) values ('+79182504748', 'г.Москва, ул.Весенная, д.2, кв.40'),
('+79253528795', 'г.Москва, ул.Лопырева, д.41, кв.87'),
('+79184715896', 'г.Казань, ул.Нероваляева, д.24, кв.24');