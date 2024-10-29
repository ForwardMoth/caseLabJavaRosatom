-- Создание таблицы
CREATE TABLE IF NOT EXISTS "user"(
    id bigint,
    name text,
    surname text,
    age bigint,
    isAdmin bool
);

-- Данные
INSERT INTO "user" (id, name, surname, age, isAdmin)
VALUES (1, 'Adam', 'Avams', 21, TRUE),
       (2, 'Wa', 'Ivanov', 10, TRUE),
       (3, 'Wadfs', 'Saimon', 21, FALSE),
       (4, 'Waner', 'Wasd', 39, FALSE),
       (5, 'Sawadfd', 'Zasd', 23, TRUE);

-- Запрос
SELECT * FROM "user"
WHERE NOT isAdmin
AND age >= 21
AND name LIKE 'Wa%'
ORDER BY surname DESC;
