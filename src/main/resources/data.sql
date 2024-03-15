INSERT INTO roles (id_role, name) VALUES (1, "ROLE_ADMIN");
INSERT INTO roles (id_role, name) VALUES (2, "ROLE_USER");


INSERT INTO users (id_user, username, password, email, group) VALUES (1, "Pepe", "$2a$12$7ykrB9Ph71jQy.gFC9swluU7QCBSpDfPQlKc8BFE53l3ZRYPIPYF2", "pepedory@gmail.com", "Family"); -- password Pepe: PepeDoryAdmin
INSERT INTO users (id_user, username, password, email, group) VALUES (2, "Pepa", "$2a$12$VABIzcdO5ziMxccrrSLuP.hvXJZfAJd0uOxpEFFURg63IoiiRceJO", "pepadory@gmail.com", "Family" ); -- password Pepa: PepaDoryUser
INSERT INTO users (id_user, username, password, email, group) VALUES (3, "Tamagochi", "$2a$12$c2tG25oP19Ekz1lIHgt/FuPw5pCx/YeE6pqMOfdPgjqBlQkTrznkG", "tamagochidory@gmail.com", "Family"); -- password Pepa: TamagochiDory

INSERT INTO groups (id, groupName, users, tasks) VALUES (1, "Family");

INSERT INTO tasks (title, description, deadline_date, group_id, assigned_user_id, is_urgent, is_completed)
VALUES ('Planificar reunión', 'Planificar la reunión de equipo para discutir el progreso del proyecto', '2023-04-15', 1, 2, true, false);

INSERT INTO roles_users (role_id, user_id) VALUES (1,1); -- Pepe es Admin
INSERT INTO roles_users (role_id, user_id) VALUES (2,2); -- Pepa es User
INSERT INTO roles_users (role_id, user_id) VALUES (2,3); -- Tamagochi es User