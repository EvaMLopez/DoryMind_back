INSERT INTO roles (id, name) VALUES (1, "ROLE_ADMIN");
INSERT INTO roles (id, name) VALUES (2, "ROLE_USER");

INSERT INTO users (id, username, password, email, group_id, role_id) VALUES (1, "Pepe", "$2a$12$7ykrB9Ph71jQy.gFC9swluU7QCBSpDfPQlKc8BFE53l3ZRYPIPYF2", "pepedory@gmail.com", 1, 1); -- password Pepe: PepeDoryAdmin
INSERT INTO users (id, username, password, email, group_id, role_id) VALUES (2, "Pepa", "$2a$12$VABIzcdO5ziMxccrrSLuP.hvXJZfAJd0uOxpEFFURg63IoiiRceJO", "pepadory@gmail.com", 1, 2); -- password Pepa: PepaDoryUser
INSERT INTO users (id, username, password, email, group_id, role_id) VALUES (3, "Tamagochi", "$2a$12$c2tG25oP19Ekz1lIHgt/FuPw5pCx/YeE6pqMOfdPgjqBlQkTrznkG", "tamagochidory@gmail.com", 1, 2); -- password Pepa: TamagochiDory

INSERT INTO user_groups (id, groupName) VALUES (1, "Family");

INSERT INTO tasks (id, title, description, deadline_date, group_id, assigned_user_id, is_urgent, is_completed)
VALUES (default, 'Planificar reunión', 'Planificar la reunión de equipo para discutir el progreso del proyecto', '2023-04-15', 1, 2, true, false);

