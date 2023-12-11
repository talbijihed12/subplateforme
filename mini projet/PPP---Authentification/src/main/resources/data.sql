insert into roles (id,name) values (1,'ROLE_USER') on conflict (id) do nothing;
insert into roles (id,name) values (2,'ROLE_ADMIN') on conflict (id) do nothing;
INSERT INTO users (created_date, email, enabled, password, username, created_by, last_modified_by, last_modified_date)
VALUES (NOW(), 'montassar.garfa.1@gmail.com', true, '$2a$10$STj7u0II9fh8r9YgQzK4yeAqwU9WbsSR/E/AI0.j9PPbWWCdk8XzW', 'jihedtalbi22', 'Montassar Garfa', 'Montassar Garfa', NOW())
    ON CONFLICT (user_id) DO NOTHING;

INSERT INTO users (created_date, email, enabled, password, username, created_by, last_modified_by, last_modified_date)
VALUES (NOW(), 'talbijihed302@gmail.com', true, '$2a$10$STj7u0II9fh8r9YgQzK4yeAqwU9WbsSR/E/AI0.j9PPbWWCdk8XzW', 'jihedtalbi', 'Jihed Talbi', 'Jihed Talbi', NOW())
    ON CONFLICT (user_id) DO NOTHING;
INSERT INTO users (created_date, email, enabled, password, username, created_by, last_modified_by, last_modified_date)
VALUES (NOW(), 'talbijihed302@gmail.com', true, '$2a$12$owpQ8BZRupVDA0XWQQgMI.dXfgKZ3klJmWgXD5SzjugGDxrJWtJSG', 'jihed', 'Jihed Talbi', 'Jihed Talbi', NOW())
    ON CONFLICT (user_id) DO NOTHING;
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2) on conflict (user_id,role_id) do nothing;
INSERT INTO user_roles( user_id, role_id)VALUES (2, 1) on conflict (user_id,role_id) do nothing;
INSERT INTO user_roles( user_id, role_id)VALUES (3, 2) on conflict (user_id,role_id) do nothing;