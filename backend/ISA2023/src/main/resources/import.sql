insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_COMPANY_ADMIN');
insert into roles (name) values ('ROLE_SUPERADMIN');

insert into companies (name,address,town,description,avg_rating) values ('prva', 'AdresaKompanije','Nis', 'OpisKompanije', 4.5);
insert into companies (name,address,town,description,avg_rating) values ('druga', 'AdresaKompanije','Nis', 'OpisKompanije', 4.5);
insert into companies (name,address,town,description,avg_rating) values ('treca', 'AdresaKompanije','Beograd', 'OpisKompanije', 4.5);

insert into users (email,password,name,surname,town,country,phone_number,profession,company_info,enabled,penalty_points,company_id,activation_token,email_confirmed) values ('korisnik@gmail.com', '$2a$10$YWHoglPTtQu.3pyBzsPP8.rLm2NZLrPW0eTIRBcWbkTlTJwusrZDK', 'Vasilije', 'Dojcinov', 'Grad', 'Država', '123456789', 'Profesija', 'Informacije', true, 0, 1,'',true);
insert into users (email,password,name,surname,town,country,phone_number,profession,company_info,enabled,penalty_points,company_id,activation_token,email_confirmed) values ('danica@gmail.com', '$2a$10$YWHoglPTtQu.3pyBzsPP8.rLm2NZLrPW0eTIRBcWbkTlTJwusrZDK', 'Danica', 'Dojcinov', 'Grad', 'Država', '123456789', 'Profesija', 'Informacije', true, 0, 1,'', true);
insert into users (email,password,name,surname,town,country,phone_number,profession,company_info,enabled,penalty_points,company_id,activation_token,email_confirmed) values ('sara@gmail.com', '$2a$10$YWHoglPTtQu.3pyBzsPP8.rLm2NZLrPW0eTIRBcWbkTlTJwusrZDK', 'Sara', 'Dojcinov', 'Grad', 'Država', '123456789', 'Profesija', 'Informacije', true, 0, 2, '', true);
insert into user_role (user_id,role_id) values(1,1);
insert into user_role (user_id,role_id) values(2,1);
insert into user_role (user_id,role_id) values(3,2);


insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('ime_admina', '1 h', '2024-07-10 14:20:00', '2024-07-10 15:20:00', false, 3, 1, 1);
insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('ime_admina', '1 h', '2024-07-10 14:20:00', '2024-07-10 15:20:00', true, 3, 1, 1);
insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('ime_admina', '1 h', '2024-02-10 14:20:00', '2024-02-10 15:20:00', true, 3, 1, 1);

insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('milos', '1 h', '2024-07-22 14:00:00', '2024-07-22 15:00:00', false, 3, 1, 2);
insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('milos', '1 h', '2024-07-22 14:00:00', '2024-07-22 15:00:00', true, 3, 1, 2);
insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('milos', '1 h', '2024-02-22 14:00:00', '2024-02-22 15:00:00', false, 3, 1, 2);

insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('petar', '1 h', '2024-7-22 11:20:00', '2024-7-22 12:20:00', false, 3, 1, 3);
insert into pickup_appointments (company_admin_full_name,duration,start_date_and_time,finish_date_and_time,is_booked,admin_id,user_id,company_id) values ('petar', '1 h', '2024-7-22 11:20:00', '2024-7-22 12:20:00', true, 3, 1, 3);

insert into equipments (name, quantity) values ('gloves', 25);
insert into equipments (name, quantity) values ('scissors', 15);
insert into equipments (name, quantity) values ('needles', 10);
insert into equipments (name, quantity) values ('masks', 10);
insert into equipments (name, quantity) values ('stethoscope', 10);

insert into company_eqipments (company_id,equipment_id) values (1, 1);
insert into company_eqipments (company_id,equipment_id) values (1, 2);
insert into company_eqipments (company_id,equipment_id) values (1, 3);
insert into company_eqipments (company_id,equipment_id) values (2, 3);
insert into company_eqipments (company_id,equipment_id) values (2, 4);
insert into company_eqipments (company_id,equipment_id) values (3, 5);

insert into users_complaints (description, company_admin_id, company_id, answer, user_id) values('aaa', 3, 1, '', 1);

commit;
