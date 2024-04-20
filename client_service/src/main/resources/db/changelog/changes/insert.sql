INSERT INTO city (name, short_name)
VALUES ('ALMATY', 'ALM');

INSERT INTO city (name, short_name)
VALUES ('ASTANA', 'AST');

INSERT INTO permission (usertype) VALUES (1);
INSERT INTO permission (usertype) VALUES (2);
INSERT INTO permission (usertype) VALUES (3);

INSERT INTO t_user (name, limit_sum, limit_datetime, limit_currency_shortname,
                  surname, client_iin, birth_date, address, balance, city_id)
VALUES ('Beksultan', 1000.0, '2024-04-20 12:00:00', 'USD',
        'Kuralbay', '1234567890', '2004-01-01', 'Almaty', 10000.0, 1);

INSERT INTO t_user_permissions (user_id, permissions_id) VALUES (1, 1);