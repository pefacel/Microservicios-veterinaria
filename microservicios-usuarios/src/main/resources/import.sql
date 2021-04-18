

INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$E3YtvEA4Rw3VRaaUgpHqH.etwWSKqgbmfJhGrfKgxlr6kux7373Xe',1, 'Pedro', 'Cerda','pefacel@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('pedro','$2a$10$oNLIVzA88IpW8dlBJ3ESZ.lWnLenOnGD2P5Wv/DXQxWoPMXKtJrG6',1, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,1);


