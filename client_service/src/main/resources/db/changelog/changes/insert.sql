INSERT INTO city (name, short_name)
VALUES ('ALMATY', 'ALM');

INSERT INTO city (name, short_name)
VALUES ('ASTANA', 'AST');

INSERT INTO permission (usertype) VALUES ('INDIVIDUAL');
INSERT INTO permission (usertype) VALUES ('LEGAL');
INSERT INTO permission (usertype) VALUES ('Individual_Entrepreneur');

INSERT INTO user (name, limit_sum, limit_datetime, limit_currency_shortname,
                  surname, clientIin, birthDate, address, balance, city_id)
VALUES ('Beksultan', 1000.0, '2024-04-20 12:00:00', 'USD',
        'Kuralbay', '1234567890', '2004-01-01', 'Almaty', 10000.0, 1);

INSERT INTO user_permission (user_id, permission_id) VALUES (1, 1);