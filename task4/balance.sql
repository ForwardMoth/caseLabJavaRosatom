-- Создание таблицы
CREATE TABLE balance(
    user_id bigint,
    amount bigint
);

-- Вставка данных
INSERT INTO balance (user_id, amount)
VALUES (1, 10),
       (2, 20),
       (3, 30);

-- Запрос
SELECT user_id
FROM balance
WHERE amount >= (
    SELECT avg(amount) * 1.5 FROM balance
);