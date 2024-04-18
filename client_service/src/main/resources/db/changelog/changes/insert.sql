INSERT INTO city (name, short_name)
VALUES ('ALMATY', 'ALM');

INSERT INTO city (name, short_name)
VALUES ('ASTANA', 'AST');

INSERT INTO permission (usertype) VALUES ('INDIVIDUAL');
INSERT INTO permission (usertype) VALUES ('LEGAL');
INSERT INTO permission (usertype) VALUES ('Individual_Entrepreneur');

INSERT INTO user (name, limit_sum, remaining_limit, limit_datetime, limit_currency_shortname,
                  surname, clientIin, birthDate, address, balance, city_id)
VALUES ('Иван', 1000.0, 500.0, '2024-04-20 12:00:00', 'USD',
        'Иванов', '1234567890', '1990-01-01', 'ул. Пушкина, д. 10', 10000.0, 1);

-- Вставляем связи пользователя с разрешениями
INSERT INTO user_permission (user_id, permission_id) VALUES (1, 1);