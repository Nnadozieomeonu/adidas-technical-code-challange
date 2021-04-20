INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/login', 'READ,WRITE', '3600', '10000', 'notification,email', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO PERMISSION (NAME) VALUES
('create_subscription'),
('read_subscription'),
('update_subscription'),
('delete_subscription');

INSERT INTO role (NAME) VALUES
('ROLE_admin'),('ROLE_client');

insert into user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'admin','{bcrypt}$2y$10$bb7aZh2OYQVsJ3kaAHtgkujyYFm0eLY9WEuf2Izb7tOnAjCpQcVGS', 'admin@admin.com', '1', '1', '1', '1');
insert into  user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'client', '{bcrypt}$2y$10$bb7aZh2OYQVsJ3kaAHtgkujyYFm0eLY9WEuf2Izb7tOnAjCpQcVGS','client@client.com', '1', '1', '1', '1');


INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
(1,1), /*create-> admin */
(2,1), /* read admin */
(3,1), /* update admin */
(4,1), /* delete admin */
(2,2),  /* read client */
(3,2);  /* update client */

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
VALUES
(1, 1) /* admin */,
(2, 2) /* client */ ;