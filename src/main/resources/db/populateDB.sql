DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, userId) VALUES
  ('2017-11-04 07:30:05', 'Завтрак', '500', 100000),
  ('2017-11-04 12:40:05', 'Обед', '1000', 100000),
  ('2017-11-04 21:10:00', 'Ужин', '510', 100000),
  ('2017-11-05 08:40:05', 'Завтрак', '510', 100000),
  ('2017-11-05 14:10:05', 'Обед', '510', 100000),
  ('2017-11-05 19:20:05', 'Ужин', '490', 100000),
  ('2017-11-04 07:30:05', 'Завтрак', '500', 100001),
  ('2017-11-04 12:40:05', 'Обед', '1000', 100001),
  ('2017-11-04 21:10:00', 'Ужин', '380', 100001),
  ('2017-11-05 08:40:05', 'Завтрак', '510', 100001),
  ('2017-11-05 14:10:05', 'Обед', '900', 100001),
  ('2017-11-05 19:20:05', 'Ужин', '600', 100001);