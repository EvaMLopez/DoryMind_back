INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_groups (id, group_name) VALUES (1, 'Family');

-- password Pepe: pepeadmin
INSERT INTO users (id, username, password, email, group_id, role_id) VALUES (1, 'Pepe', '$2a$12$tmGwtqzI6qVXPGv.d5UG5ezyDCzHCRFYMob2sIUTvZVZxGchNUR6y', 'pepedory@gmail.com', 1, 1);

-- password Pepa: PepaDoryUser
INSERT INTO users (id, username, password, email, group_id, role_id) VALUES (2, 'Pepa', '$2a$12$hBRAOZLpV2FyOa4ZngFl/O2iVSbiLh7z51yKxexwpCmCQJiYpuAfy', 'pepadory@gmail.com', 1, 2);

-- password tamagochi: Tamagochiuser
INSERT INTO users (id, username, password, email, group_id, role_id) VALUES (3, 'Tamagochi', '$2a$12$T5yRCDwYOrjMfGVL35AIPuOjKbcuVeQu/U.CB1iSjSZYywe/nLNbS', 'tamagochidory@gmail.com', 1, 2);

INSERT INTO tasks (id, title, description, deadline_date, group_id, assigned_user_id, is_urgent, is_completed) VALUES (default, 'Móvil', 'Comparar precios en El Corte Inglés, Xiaomi, Amazon... ', '2023-03-22', 1, 2, false, true);

INSERT INTO tasks (id, title, description, deadline_date, group_id, assigned_user_id, is_urgent, is_completed) VALUES (default, 'Cumpleaños', 'Comprar regalo a Tamagochi', '2023-04-15', 1, 1, false, false);

INSERT INTO tasks (id, title, description, deadline_date, group_id, assigned_user_id, is_urgent, is_completed) VALUES (default, 'Deberes', 'Hacer los deberes para poder ir al cine el sábado', '2023-03-29', 1, 3, true, false);