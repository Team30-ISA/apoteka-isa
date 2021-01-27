insert into pharmacy (name, address) values ('Helenina Apoteka','Teslina ulica 34');
insert into pharmacy (name, address) values ('Nikolina Apoteka', 'Njegoševa 8');
insert into pharmacy (name, address) values ('Milanova Apoteka', 'Petra Konjovica 1');
insert into pharmacy (name, address) values ('Aleksandrova Apoteka', 'Laze Lazarevica 234');


INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (3, 'ale', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Aleksandra', 'Gojkovic', 'apoteka.isa2021+ale@gmail.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (4,'jeca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jelena', 'Cvetkovska', 'apoteka.isa2021+jeca@gmail.com', true, '2017-10-01 18:57:58.508-07',1);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (5, 'mile', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Micic', 'apoteka.isa2021+mile@gmail.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (6, 'pero', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrovic', 'apoteka.isa2021+pero@gmail.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (7,'a', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mila', 'Peric', 'apoteka.isa2021+a@gmail.com', true, '2017-10-01 18:57:58.508-07',1);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (8,'b', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vasa', 'Vasic', 'apoteka.isa2021+b@gmail.com', true, '2017-10-01 18:57:58.508-07',2);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (9,'c', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nenad', 'Milic', 'apoteka.isa2021+c@gmail.com', true, '2017-10-01 18:57:58.508-07',3);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (10,'marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'apoteka.isa2021+marko@gmail.com', true, '2017-10-01 18:57:58.508-07',1);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (11,'ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mikic', 'apoteka.isa2021+ana@gmail.com', true, '2017-10-01 18:57:58.508-07',2);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id) VALUES (12,'adam', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Adam', 'Adamovic', 'apoteka.isa2021+adam@gmail.com', true, '2017-10-01 18:57:58.508-07',3);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (13,'pacijent1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milena', 'Vracar', 'apoteka.isa2021+m@gmail.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (14,'pacijent2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Matija', 'Vracar', 'apoteka.isa2021+matija@gmail.com', true, '2017-10-01 18:57:58.508-07');
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (15,'pacijent3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marjan', 'Vracar', 'apoteka.isa2021+marjan@gmail.com', true, '2017-10-01 18:57:58.508-07');

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4, 4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (10, 5);

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

insert into promotion_notification (patient_id, pharmacy_id) values (13, 1);
insert into promotion_notification (patient_id, pharmacy_id) values (14, 2);
insert into promotion_notification (patient_id, pharmacy_id) values (15, 3);
