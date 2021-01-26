insert into pharmacy (name, address) values ('Helenina Apoteka','Teslina ulica 34');
insert into pharmacy (name, address) values ('Nikolina Apoteka', 'Njegoševa 8');
insert into pharmacy (name, address) values ('Milanova Apoteka', 'Petra Konjovica 1');
insert into pharmacy (name, address) values ('Aleksandrova Apoteka', 'Laze Lazarevica 234');

INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (3, 'ale', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Aleksandra', 'Gojkovic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (4,'jeca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jelena', 'Cvetkovska', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07',1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (5, 'mile', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Micic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (6, 'pero', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrovic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (7,'a', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mila', 'Peric', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07',1);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (8,'b', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vasa', 'Vasic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07',2);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (9,'c', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nenad', 'Milic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07',3);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9, 4);

insert into pharmacy_dermatologist (user_id, pharmacy_id) values (3,1);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (5,2);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (6,2);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (3,2);