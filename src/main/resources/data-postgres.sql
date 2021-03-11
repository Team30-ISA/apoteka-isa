INSERT INTO Country (id, country) VALUES (1, 'Srbija');
INSERT INTO Country (id, country) VALUES (2, 'Bosna i Hercegovina');
INSERT INTO Country (id, country) VALUES (3, 'Makedonija');

INSERT INTO City (id, city, country_id) VALUES (1, 'Subotica', 1);
INSERT INTO City (id, city, country_id) VALUES (2, 'Novi Sad', 1);
INSERT INTO City (id, city, country_id) VALUES (3, 'Beograd', 1);
INSERT INTO City (id, city, country_id) VALUES (4, 'Zrenjanin', 1);
INSERT INTO City (id, city, country_id) VALUES (5, 'Mostar', 2);
INSERT INTO City (id, city, country_id) VALUES (6, 'Sarajevo', 2);
INSERT INTO City (id, city, country_id) VALUES (7, 'Skoplje', 3);
INSERT INTO City (id, city, country_id) VALUES (8, 'Kumanovo', 3);
INSERT INTO City (id, city, country_id) VALUES (9, 'Šabac', 1);

INSERT INTO Address (id, street, city_id) VALUES (6, 'Beogradski put 24', 1);
INSERT INTO Address (id, street, city_id) VALUES (7, 'Zetska 10', 1);
INSERT INTO Address (id, street, city_id) VALUES (8, 'Bulevar oslobodjenja 52', 2);
INSERT INTO Address (id, street, city_id) VALUES (9, 'Brace Ribnikara 65', 2);

insert into pharmacy (name, street, city, description, counselingPrice, grade) values ('Helenina Apoteka','Beogradski put 24','Subotica', 'Helenina Apoteka je apoteka koju odlikuje nov i moderan pristup u sprovođenju farmaceutske usluge u oblasti medicinske zaštite i prevencije. ', 500.89, 10);
insert into pharmacy (name, street, city, description, counselingPrice, grade) values ('Nikolina Apoteka','Zetska 10', 'Subotica', 'Helenina Apoteka je apoteka koju odlikuje nov i moderan pristup u sprovođenju farmaceutske usluge u oblasti medicinske zaštite i prevencije. ', 467.00, 9);
insert into pharmacy (name, street, city, description, counselingPrice, grade) values ('Milanova Apoteka','Lasla Gala 22','Novi Sad', 'Helenina Apoteka je apoteka koju odlikuje nov i moderan pristup u sprovođenju farmaceutske usluge u oblasti medicinske zaštite i prevencije. ', 668.10,8);
insert into pharmacy (name, street, city, description, counselingPrice, grade) values ('Aleksandrova Apoteka' ,'Cirpanova 34', 'Novi Sad', 'Helenina Apoteka je apoteka koju odlikuje nov i moderan pristup u sprovođenju farmaceutske usluge u oblasti medicinske zaštite i prevencije.', 900.99,7);



INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (3, 'ale', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Aleksandra', 'Gojkovic', 'apoteka.isa2021+ale@gmail.com', true, '2017-10-01 18:57:58.508-07', 0, 6, 10, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (5, 'mile', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Mile', 'Micic', 'apoteka.isa2021+mile@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6, 9, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (6, 'pero', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Petar', 'Petrovic', 'apoteka.isa2021+pero@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6, 8, 0);

INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version, grade) VALUES (4,'jeca', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Jelena', 'Cvetkovska', 'apoteka.isa2021+jeca@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 0, 6, 0, 10);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version, grade) VALUES (7,'a', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Mila', 'Peric', 'apoteka.isa2021+a@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 0, 6, 0, 9);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version, grade) VALUES (8,'b', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Vasa', 'Vasic', 'apoteka.isa2021+b@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 1, 6, 0, 8);
INSERT INTO pharmacist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version, grade) VALUES (9,'c', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Nenad', 'Milic', 'apoteka.isa2021+c@gmail.com', true, '2017-10-01 18:57:58.508-07',3, 1, 6, 0, 7);



INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version) VALUES (10,'marko', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Marko', 'Markovic', 'apoteka.isa2021+marko@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 1, 6, 0);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version) VALUES (11,'ana', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Ana', 'Mikic', 'apoteka.isa2021+ana@gmail.com', true, '2017-10-01 18:57:58.508-07',2, 0, 7, 0);
INSERT INTO pharmacyAdmin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, pharmacy_id, gender, address_id, version) VALUES (12,'adam', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Adam', 'Adamovic', 'apoteka.isa2021+adam@gmail.com', true, '2017-10-01 18:57:58.508-07',1, 1, 8, 0);

INSERT INTO patient (id, username, password, first_name, last_name, email, address_id, enabled, last_password_reset_date, gender, version) VALUES (13,'milena', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Milena', 'Vracar', 'apoteka.isa2021+m1@gmail.com', 7, true, '2017-10-01 18:57:58.508-07', 0, 0);
INSERT INTO patient (id, username, password, first_name, last_name, email, address_id, enabled, last_password_reset_date, gender, version) VALUES (14,'matija', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Matija', 'Matic', 'apoteka.isa2021+matija@gmail.com', 8,  true, '2017-10-01 18:57:58.508-07', 1, 0);
INSERT INTO patient (id, username, password, first_name, last_name, email, address_id, enabled, last_password_reset_date, gender, version) VALUES (15,'marjan', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Marjan', 'Maric', 'apoteka.isa2021+marjan@gmail.com', 9, true, '2017-10-01 18:57:58.508-07', 1, 0);
--id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, version
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (17, 'admin1', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Natasa', 'Gojkovic', 'apoteka.isa2021+natasa@gmail.com', true, '2017-10-01 18:57:58.508-07', 0, 6, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (18, 'admin2', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Ivan', 'Gojkovic', 'apoteka.isa2021+ivan@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (19, 'admin3', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale1@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 8, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (20, 'admin4', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale2@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (21, 'admin5', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale3@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (22, 'admin6', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale4@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 8, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (23, 'admin7', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale5@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 6, 0, 0);
INSERT INTO dermatologist (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, grade, version) VALUES (24, 'admin8', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'apoteka.isa2021+strale6@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0, 0);
INSERT INTO system_admin (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, version) VALUES (25, 'System Admin', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Gojkovic', 'sysadmin@gmail.com', true, null, 1, 7, 0);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARM');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYS_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPL');

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
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (25, 6);

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
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Fervex', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Aspirin', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Coldrex', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Eutirox', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);
insert into medicine (name, type_id, form_id, contraindications, composition, recommended_intake_per_day, manufacturer, regime) VALUES ('Tetafex', 4, 3, 'allergy', '200mg ibuprofen, 325mg paracetamol', 'Po potrebi, ali ne više od 2', 'GOODWILL', 0);

insert into patient_allergies(patient_id, allergies_id) VALUES (13, 1);

insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (1, 2);
insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (2, 1);
insert into medicine_substitutes (medicine_id, sub_medicine_id) VALUES (3, 1);

insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (10, 2, 1);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (6, 2, 2);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (6, 2, 3);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (20, 1, 2);  
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (0, 1, 5);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (0, 1, 7);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (7, 4, 3);
insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (8, 3, 2);
insert into reserved_medicine (uid, quantity, date, patient_id, medicine_id, pharmacy_id, approved) VALUES ('9441acaf-c350-421e-a33d-c64522e5135f', 1, '20210218 10:00:00 AM', 13, 3, 1, true);
insert into reserved_medicine (uid, quantity, date, patient_id, medicine_id, pharmacy_id, approved) VALUES ('12', 1, '20210210 10:00:00 AM', 13, 3, 1, true);
insert into reserved_medicine (uid, quantity, date, patient_id, medicine_id, pharmacy_id, approved) VALUES ('13', 1, '20210211 11:03:00 AM', 13, 2, 1, true);
insert into reserved_medicine (uid, quantity, date, patient_id, medicine_id, pharmacy_id, approved) VALUES ('14', 1, '20210212 10:00:00 AM', 13, 1, 1, true);
insert into reserved_medicine (uid, quantity, date, patient_id, medicine_id, pharmacy_id, approved) VALUES ('15', 1, '20210212 10:00:00 AM', 13, 2, 1, false);

insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date, version) VALUES (3, 1, '20210218 10:00:00 AM', '20210218 10:00:00 PM', 0);
insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date, version) VALUES (3, 2, '20210219 10:00:00 AM', '20210219 10:00:00 PM', 0);
insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date, version) VALUES (5, 2, '20210220 05:00:00 AM', '20210220 05:00:00 PM', 0);
insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date, version) VALUES (5, 1, '20210220 05:00:00 AM', '20210220 05:00:00 PM', 0);
insert into dermatologist_work_calendar (dermatologist_id, pharmacy_id, start_date, end_date, version) VALUES (5, 2, '20210215 05:00:00 AM', '20210215 05:00:00 PM', 0);

insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200108 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200108 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200108 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200408 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200308 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200308 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200808 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200708 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20200608 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20201008 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20201008 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20201108 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20201208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20201208 08:53:00 PM', 5, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20201208 08:53:00 PM', 5, 1, 1999.99, '', 0); 

insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id, report, version) VALUES ('20210204 11:35:00 AM', 60, 2, 999.99, 13, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id, report, version) VALUES ('20210219 12:00:00 PM', 30, 1, 999.99, 13, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, report, version) VALUES ('20210220 11:00:00 AM', 15, 1, 1999.99, 'nesto', 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id, version) VALUES ('20210220 11:15:00 AM', 30, 1, 1999.99, 13, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 12:00:00 PM', 60, 1, 2999.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 01:00:00 PM', 5, 1, 99.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 01:05:00 PM', 15, 1, 999.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 01:20:00 PM', 30, 1, 699.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 01:50:00 PM', 10, 1, 599.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 02:00:00 PM', 20, 1, 799.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 03:30:00 PM', 25, 1, 899.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 03:55:00 PM', 5, 1, 99.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 05:00:00 PM', 15, 1, 999.99, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210220 01:00:00 PM', 5, 4, 68.00, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, version) VALUES ('20210208 09:00:00 PM', 5, 4, 2.00, 0);
insert into counseling (start_date, duration, dermatologist_work_calendar_id, price, patient_id, version) VALUES ('20210220 11:12:00 AM', 20, 3, 500.00, 13, 0);

insert into promotion_notification (patient_id, pharmacy_id) values (13, 1);
insert into promotion_notification (patient_id, pharmacy_id) values (15, 3);
insert into promotion_notification (patient_id, pharmacy_id) values (13, 2);
insert into promotion_notification (patient_id, pharmacy_id) values (15, 2);
insert into promotion_notification (patient_id, pharmacy_id) values (13, 4);

insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date, version) VALUES (4, 1, '20210215 10:00:00 AM', '20210215 08:00:00 PM', 0);
insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date, version) VALUES (4, 1, '20210216 08:00:00 AM', '20210216 08:00:00 PM', 0);
insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date, version) VALUES (4, 1, '20210217 04:00:00 PM', '20210218 04:00:00 AM', 0);
insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date, version) VALUES (4, 1, '20210208 08:00:00 AM', '20210208 08:00:00 PM', 0);
insert into pharmacist_work_calendar (pharmacist_id, pharmacy_id, start_date, end_date, version) VALUES (4, 1, '20210210 04:00:00 PM', '20210211 04:00:00 AM', 0);

insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210205 12:35:00 PM', 30, 1, 99.99, 13);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210215 11:05:00 AM', 15, 1, 999.99, 14);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210215 02:20:00 PM', 30, 1, 699.99, 15);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210216 01:50:00 PM', 10, 2, 599.99, 13);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210216 02:00:00 PM', 20, 2, 799.99, 14);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210217 05:30:00 PM', 25, 3, 899.99, 15);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210217 05:55:00 PM', 5, 3, 99.99, 13);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210218 03:00:00 AM', 15, 3, 999.99, 14);
insert into examination (start_date, duration, pharmacist_work_calendar_id, price, patient_id) VALUES ('20210210 05:00:00 PM', 15, 5, 999.99, 14);

insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (1, 2, 3, 13);
insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (2, 5, 5, 13);
insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (3, 4, 6, 13);
insert into dermatologist_grade (id, grade, dermatologist_id, patient_id) values (4, 3, 3, 13);

insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (1, 5, 4, 13);   
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (7, 2, 4, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (2, 5, 4, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (3, 4, 7, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (4, 3, 7, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (5, 1, 8, 13);
insert into pharmacist_grade (id, grade, pharmacist_id, patient_id) values (6, 2, 8, 13);

insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (100, '20210216 08:00:00 PM',200, '20210215 10:00:00 AM', 1, 2);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (101, '20210216 08:00:00 PM',300, '20210215 10:00:00 AM', 2, 1);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (102, '20210216 08:00:00 PM',400, '20210215 10:00:00 AM', 2, 2);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (103, '20210216 08:00:00 PM',400, '20210215 10:00:00 AM', 2, 3);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (104, '20210216 08:00:00 PM',400, '20210215 10:00:00 AM', 3, 3);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (105, '20210216 08:00:00 PM',0, '20210215 10:00:00 AM', 5, 1);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (106, '20210216 08:00:00 PM',0, '20210215 10:00:00 AM', 7, 1);
/*
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (105, '20210216 08:00:00 PM',400, '20210215 10:00:00 AM', 2, 4);
insert into medicine_price(id, end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (106, '20210216 08:00:00 PM',400, '20210215 10:00:00 AM', 3, 4);
*/

INSERT INTO supplier (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, version) VALUES (26, 'strale', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Strahinja', 'Nikolic', 'apoteka.isa2021+stra@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0);
INSERT INTO supplier (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, version) VALUES (27, 'jana', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Jana', 'Nikolic', 'apoteka.isa2021+jana@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0);
INSERT INTO supplier (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, version) VALUES (28, 'tara', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Tara', 'Taric', 'supplier@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0);
INSERT INTO supplier (id, username, password, first_name, last_name, email, enabled, last_password_reset_date, gender, address_id, version) VALUES (29, 'mile', '$2a$10$2affw9vkXuEbQOoiKqN2XuVQ1sVZLQ6XBRAZ1IHeCT6W8.qJOXkhe', 'Mile', 'Milic', 'apoteka.isa2021+milence@gmail.com', true, '2017-10-01 18:57:58.508-07', 1, 7, 0);

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (26, 7);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (27, 7);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (28, 7);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (29, 7);

insert into errand (id, creation_time, deadline, pharmacy_id, finished, admin_id) values (1, '2021-03-10 10:38:09.147', '2021-04-06 20:01:00', 1, false,12);
insert into errand (id, creation_time, deadline, pharmacy_id, finished, admin_id) values (10, '2021-03-10 10:38:09.147', '2021-04-06 20:01:00', 1, false,12);
insert into errand (id, creation_time, deadline, pharmacy_id, finished, admin_id) values (11, '2021-03-10 10:38:09.147', '2021-03-29 20:01:00', 2, false,10);
insert into errand (id, creation_time, deadline, pharmacy_id, finished, admin_id) values (12, '2021-03-10 10:38:09.147', '2021-03-29 20:01:00', 3, false,10);
insert into errand (id, creation_time, deadline, pharmacy_id, finished, admin_id) values (13, '2021-03-10 10:38:09.147', '2021-03-29 20:01:00', 1, false,10);

insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (17, 1, 12, 2);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (18, 2, 12, 3);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (19, 1, 13, 2);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (20, 2, 13, 4);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (10, 5, 10, 2);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (12, 7, 10, 5);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (13, 3, 10, 7);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (14, 5, 11, 2);   
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (15, 7, 11, 5);
insert into medicine_quantity (id, quantity, errand_id, medicine_id) values (16, 7, 11, 7);

insert into offer (id, price, supply_deadline, errand_id, supplier_id, is_approved) values (10, 10000, '2021-04-05 01:00:00', 10, 26, null);
insert into offer (id, price, supply_deadline, errand_id, supplier_id, is_approved) values (11, 10000, '2021-03-25 01:00:00', 11, 27, null);
insert into offer (id, price, supply_deadline, errand_id, supplier_id, is_approved) values (12, 10000, '2021-04-05 01:00:00', 11, 26, false);
insert into offer (id, price, supply_deadline, errand_id, supplier_id, is_approved) values (14, 14000, '2021-03-25 01:00:00', 10, 27, true);
insert into offer (id, price, supply_deadline, errand_id, supplier_id, is_approved) values (15, 15000, '2021-03-26 01:00:00', 1, 26, true);

insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (1, 1, 1, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (2, 3, 1, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (3, 5, 2, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (4, 1, 2, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (5, 2, 3, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (6, 4, 3, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (7, 5, 4, 13);
insert into pharmacy_grade (id, grade, pharmacy_id, patient_id) values (8, 1, 4, 13);

insert into dermatologist_holiday(start_date, end_date, dermatologist_id, status) values ('20210210 12:00:00 AM','20210213 12:00:00 PM',5, 2);
insert into pharmacist_holiday(start_date, end_date, pharmacist_id, status) values ('20210210 12:00:00 AM','20210213 12:00:00 PM',4, 2);


insert into notification(date, user_id, message) values ('20210210 12:00:00 AM', 10, 'Nema vise bensedina');
insert into notification(date, user_id, message) values ('20210210 12:00:00 AM', 10, 'Fali brufen 400mg');

insert into supplier_medicine (id, quantity, supplier_id, medicine_id) VALUES (1, 6, 27, 1);
insert into supplier_medicine (id, quantity, supplier_id, medicine_id) VALUES (2, 8, 27, 2);
insert into supplier_medicine (id, quantity, supplier_id, medicine_id) VALUES (3, 10, 27, 3); 

