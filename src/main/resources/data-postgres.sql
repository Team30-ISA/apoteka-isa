insert into pharmacy (name, address) values ('Helenina Apoteka','Teslina ulica 34');
insert into pharmacy (name, address) values ('Nikolina Apoteka', 'Njegoševa 8');
insert into pharmacy (name, address) values ('Milanova Apoteka', 'Petra Konjovica 1');
insert into pharmacy (name, address) values ('Aleksandrova Apoteka', 'Laze Lazarevica 234');

INSERT INTO Country (id, country) VALUES (1, 'Srbija');
INSERT INTO Country (id, country) VALUES (2, 'Bosna i Hercegovina');

INSERT INTO City (id, city, country_id) VALUES (1, 'Subotica', 1);
INSERT INTO City (id, city, country_id) VALUES (2, 'Novi Sad', 1);
INSERT INTO City (id, city, country_id) VALUES (3, 'Beograd', 1);
INSERT INTO City (id, city, country_id) VALUES (4, 'Mostar', 2);

INSERT INTO Address (id, street, city_id) VALUES (1, 'Mirka Lazica 3a', 1);
INSERT INTO Address (id, street, city_id) VALUES (2, 'Kumiciceva 6', 1);
INSERT INTO Address (id, street, city_id) VALUES (3, 'Laze Kostica 9', 2);

INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (3, 'ale', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Aleksandra', 'Gojkovic', 'apoteka.isa2021+ale@gmail.com', true, '2017-10-01 18:57:58.508-07', 0);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender) VALUES (4,'jeca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jelena', 'Cvetkovska', 'apoteka.isa2021+jeca@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (5, 'mile', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Micic', 'apoteka.isa2021+mile@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (6, 'pero', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrovic', 'apoteka.isa2021+pero@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender) VALUES (7,'a', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mila', 'Peric', 'apoteka.isa2021+a@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 0);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender) VALUES (8,'b', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vasa', 'Vasic', 'apoteka.isa2021+b@gmail.com', true, '2017-10-01 18:57:58.508-07',2, 1);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender) VALUES (9,'c', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nenad', 'Milic', 'apoteka.isa2021+c@gmail.com', true, '2017-10-01 18:57:58.508-07',3, 1);

INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (10,'marko', '$2y$12$AGb2DqsLBf1789IrJitsuejVoweLLnmIKHPjcVY1HmnNMydFQysF.', 'Marko', 'Markovic', 'apoteka.isa2021+marko@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 1, 1);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (11,'ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mikic', 'apoteka.isa2021+ana@gmail.com', true, '2017-10-01 18:57:58.508-07',2, 0, 2);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (12,'adam', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Adam', 'Adamovic', 'apoteka.isa2021+adam@gmail.com', true, '2017-10-01 18:57:58.508-07',3, 1, 3);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (13,'pacijent1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milena', 'Vracar', 'apoteka.isa2021+m@gmail.com', true, '2017-10-01 18:57:58.508-07', 0);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (14,'pacijent2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Matija', 'Vracar', 'apoteka.isa2021+matija@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (15,'pacijent3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marjan', 'Vracar', 'apoteka.isa2021+marjan@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO patient (id, username, password, first_name, last_name, email, address_id, enabled, last_password_reset_date, gender) VALUES (16,'pacijent123', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Gojko', 'Marinkovic', 'apoteka.isa2021+m@gmail.com', 1, true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (17, 'admin1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Natasa', 'Gojkovic', 'apoteka.isa2021+natasa@gmail.com', true, '2017-10-01 18:57:58.508-07', 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (18, 'admin2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ivan', 'Gojkovic', 'apoteka.isa2021+ivan@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (19, 'admin3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (20, 'admin4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (21, 'admin5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (22, 'admin6', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (23, 'admin7', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (24, 'admin8', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (16, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (13, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (14, 5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (15, 5);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (10, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (11, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (12, 2);

insert into pharmacy_dermatologist (user_id, pharmacy_id) values (3,1);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (5,2);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (6,2);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (3,2);

insert into drug_form (name) VALUES ('antibiotic');
insert into drug_form (name) VALUES ('anesthetic');
insert into drug_form (name) VALUES ('antihistamine');
insert into drug_form (name) VALUES ('analgesic');

insert into drug_type (name) VALUES ('powder');
insert into drug_type (name) VALUES ('capsule');
insert into drug_type (name) VALUES ('tablet');
insert into drug_type (name) VALUES ('ointment');
insert into drug_type (name) VALUES ('paste');
insert into drug_type (name) VALUES ('gel');
insert into drug_type (name) VALUES ('solution');
insert into drug_type (name) VALUES ('syrup');

insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Brufen', 4, 3, 'nausea', '500mg ibuprofen', 'Po potrebi, ali ne više od 3', 'Galenika', 0);
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Metafex', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);

insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (1, 2);
insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (2, 1);

insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (10, 2, 1);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (6, 2, 2);
