INSERT INTO transaction
    (id, sum, account_to, account_from, currency_shortname,
     dateTime, expense_category, limit_exceeded, current_currency_sum,
     remaining_limit)
VALUES
('1', 1000.0, 123456789, 1, 'KZT', '2024-04-13 12:00:00', 'Food', false, 1000.0, 10.0),
('2', 2000.0, 987654321, 1, 'KZT', '2024-04-13 12:00:00', 'Shopping', false, 2000.0, 20.0);
