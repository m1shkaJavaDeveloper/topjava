DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meals_seq RESTART WITH 0;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO MEALS (dateTime, description, calories) VALUES
  ('2020-01-30 10:00', 'Завтрак', 500),
  ('2020-01-30 13:00', 'Обед', 1000),
  ('2020-01-30 20:00', 'Ужин', 500),
  ('2020-01-31 0:00', 'Еда на граничное значение', 100),
  ('2020-01-31 10:00', 'Завтрак', 1000),
  ('2020-01-31 13:00', 'Обед', 500),
  ('2020-01-31 20:00', 'Ужин', 410);
