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

INSERT INTO Address (id, street, city_id) VALUES (6, 'Mirka Lazica 3a', 1);
INSERT INTO Address (id, street, city_id) VALUES (7, 'Kumiciceva 6', 1);
INSERT INTO Address (id, street, city_id) VALUES (8, 'Laze Kostica 9', 2);
INSERT INTO Address (id, street, city_id) VALUES (9, 'Kisacka 9', 2);

INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (3, 'ale', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Aleksandra', 'Gojkovic', 'apoteka.isa2021+ale@gmail.com', true, '2017-10-01 18:57:58.508-07', 0, 6);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (5, 'mile', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Micic', 'apoteka.isa2021+mile@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (6, 'pero', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Petrovic', 'apoteka.isa2021+pero@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (4,'jeca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jelena', 'Cvetkovska', 'apoteka.isa2021+jeca@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 0,6);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (7,'a', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mila', 'Peric', 'apoteka.isa2021+a@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 0, 6);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (8,'b', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Vasa', 'Vasic', 'apoteka.isa2021+b@gmail.com', true, '2017-10-01 18:57:58.508-07',2, 1, 6);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (9,'c', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nenad', 'Milic', 'apoteka.isa2021+c@gmail.com', true, '2017-10-01 18:57:58.508-07',3, 1, 6);

INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (10,'marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'apoteka.isa2021+marko@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 1, 6);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (11,'ana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ana', 'Mikic', 'apoteka.isa2021+ana@gmail.com', true, '2017-10-01 18:57:58.508-07',2, 0, 7);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id) VALUES (12,'adam', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Adam', 'Adamovic', 'apoteka.isa2021+adam@gmail.com', true, '2017-10-01 18:57:58.508-07',3, 1, 8);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (13,'pacijent1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Milena', 'Vracar', 'apoteka.isa2021+m@gmail.com', true, '2017-10-01 18:57:58.508-07', 0, 8);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (14,'pacijent2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Matija', 'Vracar', 'apoteka.isa2021+matija@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO patient (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender) VALUES (15,'pacijent3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marjan', 'Vracar', 'apoteka.isa2021+marjan@gmail.com', true, '2017-10-01 18:57:58.508-07', 1);
INSERT INTO patient (id, username, password, first_name, last_name, email, address_id, enabled, last_password_reset_date, gender) VALUES (16,'pacijent123', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Gojko', 'Marinkovic', 'apoteka.isa2021+m@gmail.com', 6, true, '2017-10-01 18:57:58.508-07', 1);

INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (17, 'admin1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Natasa', 'Gojkovic', 'apoteka.isa2021+natasa@gmail.com', true, '2017-10-01 18:57:58.508-07', 0, 6);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (18, 'admin2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Ivan', 'Gojkovic', 'apoteka.isa2021+ivan@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (19, 'admin3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 8);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (20, 'admin4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (21, 'admin5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (22, 'admin6', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 8);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (23, 'admin7', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id) VALUES (24, 'admin8', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5, 3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6, 3);
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
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (17,1);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (18,1);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (19,1);
insert into pharmacy_dermatologist (user_id, pharmacy_id) values (20,1);

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
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Tetafex', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);

insert into patient_allergies(patient_id, allergies_id) VALUES (13, 1);

insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (1, 2);
insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (2, 1);
insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (3, 1);

insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (10, 2, 1);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (6, 2, 2);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (20, 1, 2);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (5, 4, 2);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (21, 3, 3);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (7, 4, 3);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (8, 3, 2);
insert into reserved_medicine (uid, quantity, date, patient_id, medicine_id) VALUES ('9441acaf-c350-421e-a33d-c64522e5135f', 1, '20210218 10:00:00 AM', 13, 3);

insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date) VALUES (3, 1, '20210218 10:00:00 AM', '20210218 10:00:00 PM');
insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date) VALUES (3, 2, '20210219 10:00:00 AM', '20210219 10:00:00 PM');
insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date) VALUES (5, 2, '20210220 05:00:00 AM', '20210220 05:00:00 PM');

insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210208 08:53:00 PM', 5, 1, 1999.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id) VALUES ('20210204 11:35:00 AM', 60, 2, 999.99, 13);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id) VALUES ('20210219 12:00:00 PM', 30, 2, 999.99, 13);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 11:00:00 AM', 15, 1, 1999.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id) VALUES ('20210220 11:15:00 AM', 30, 1, 1999.99, 13);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 12:00:00 PM', 60, 1, 2999.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 01:00:00 PM', 5, 1, 99.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 01:05:00 PM', 15, 1, 999.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 01:20:00 PM', 30, 1, 699.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 01:50:00 PM', 10, 1, 599.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 02:00:00 PM', 20, 1, 799.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 03:30:00 PM', 25, 1, 899.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 03:55:00 PM', 5, 1, 99.99);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price) VALUES ('20210220 05:00:00 PM', 15, 1, 999.99);

insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id) VALUES ('20210220 11:12:00 AM', 20, 3, 500.00, 13);

insert into promotion_notification (patient_id, pharmacy_id) values (13, 1);
insert into promotion_notification (patient_id, pharmacy_id) values (14, 2);
insert into promotion_notification (patient_id, pharmacy_id) values (15, 3);

insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date) VALUES (4, 1, '20210215 10:00:00 AM', '20210215 08:00:00 PM');
insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date) VALUES (4, 1, '20210216 08:00:00 AM', '20210216 08:00:00 PM');
insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date) VALUES (4, 1, '20210217 04:00:00 PM', '20210218 04:00:00 AM');

insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210205 12:35:00 PM', 30, 1, 99.99, 13);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210215 11:05:00 AM', 15, 1, 999.99, 14);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210215 02:20:00 PM', 30, 1, 699.99, 15);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210216 01:50:00 PM', 10, 2, 599.99, 13);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210216 02:00:00 PM', 20, 2, 799.99, 14);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210217 05:30:00 PM', 25, 3, 899.99, 15);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210217 05:55:00 PM', 5, 3, 99.99, 13);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210218 03:00:00 AM', 15, 3, 999.99, 14);

insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (1, 2, 3, 13);
insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (2, 5, 5, 13);
insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (3, 4, 6, 13);
insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (4, 3, 3, 13);


insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (1, 2, 4, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (2, 5, 4, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (3, 4, 7, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (4, 3, 7, 13);

insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (100, '20210216 08:00:00 PM',200, '20210215 10:00:00 AM', 1, 2);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (101, '20210216 08:00:00 PM',300, '20210215 10:00:00 AM', 2, 1);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (102, '20210216 08:00:00 PM',400, '20210215 10:00:00 AM', 2, 2);