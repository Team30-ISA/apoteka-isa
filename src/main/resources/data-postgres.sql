insert into pharmacy (name, address) values ('Helenina Apoteka','Teslina ulica 34');
insert into pharmacy (name, address) values ('Nikolina Apoteka', 'Njegoševa 8');
insert into pharmacy (name, address) values ('Milanova Apoteka', 'Petra Konjovica 1');
insert into pharmacy (name, address) values ('Aleksandrova Apoteka', 'Laze Lazarevica 234');

INSERT INTO COUNTRIES (country_id, name) values (1, 'Srbija');


INSERT INTO CITIES (city_id, name, country_id) values (1, 'Novi Sad', 1);

INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (11, 10, 'Kisačka', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (2, 10, 'Bulevar Oslobodjenja', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (3, 10, 'Balzakova', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (4, 10, 'Stražilovska', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (5, 10, 'Danila Kiša', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (6, 10, 'Radnička', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (7, 10, 'Stevana Sremca', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (8, 10, 'Patrijarha Pavla', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (9, 10, 'Ćirpanova', 1, 1);
INSERT INTO ADDRESSES (address_id, number, street, country_id, city_id) values (10, 10, 'Maksima Gorkog', 1, 1);

INSERT INTO dermatologist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone,country_id, city_id, address_id) VALUES (3,  '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Aleksandra', 'Gojkovic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07','0653446453', 1, 1,11);
INSERT INTO dermatologist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone, country_id, city_id, address_id) VALUES (5,  '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Micic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07', '0633456454', 1,1, 3);
INSERT INTO dermatologist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone, country_id, city_id, address_id) VALUES (6, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrovic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07', '0623456453',1,1, 4);
INSERT INTO pharmacist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone, country_id, address_id, city_id, pharmacy_id) VALUES (4, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jelena', 'Cvetkovska', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07', '0623456453', 1, 2, 1,1);
INSERT INTO pharmacist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone,country_id, address_id, city_id, pharmacy_id) VALUES (7, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mila', 'Peric', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07', '0623456453',1, 5, 1,1);
INSERT INTO pharmacist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone,country_id, address_id, city_id, pharmacy_id) VALUES (8, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vasa', 'Vasic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07','0623456453', 1, 6,1, 2);
INSERT INTO pharmacist (id, password, first_name, last_name, email, enabled, last_password_reset_date, telephone,country_id, address_id, city_id, pharmacy_id) VALUES (9, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nenad', 'Milic', 'jeca@example.com', true, '2017-10-01 18:57:58.508-07','0623456453', 1, 7, 1,3);

-- password je petar
INSERT INTO USERS (id, email, enabled, first_name, last_name, last_password_reset_date, password, telephone, address_id, city_id, country_id) VALUES (1, 'pera@example.com', true, 'Petar', 'Dragoljević', '2017-10-01 18:57:58.508-07', '$2a$10$.4TlEcGSbL2ibKLkKMBqo.otZTShm8UqMjkpOK3Lpl42y3BH0m56q', '0623456453', 10, 1, 1);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM');
insert into AUTHORITY (name) values ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);


insert into pharmacy_dermatologist (user_id, pharmacy_id) values (3,1);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (5,2);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (6,2);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (3,2);
