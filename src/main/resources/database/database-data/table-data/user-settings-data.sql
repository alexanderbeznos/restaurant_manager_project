--liquibase formatted sql

--changeSet abeznos:user-settings-data-01 logicalFilePath:database\database-data\user-settings-data
INSERT INTO user_settings (phone, address) values ('', ''),
('', ''),
('+79253528795', 'г.Москва, ул.Лопырева, д.41, кв.87'),
('+79184715896', 'г.Москва, ул.Весенная, д.24, кв.24');