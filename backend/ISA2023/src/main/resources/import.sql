
insert into companies (name,address,town,description,avg_rating) values ('NazivKompanije', 'AdresaKompanije','Nis', 'OpisKompanije', 4.5);
insert into companies (name,address,town,description,avg_rating) values ('prva', 'AdresaKompanije','Nis', 'OpisKompanije', 4.5);
insert into companies (name,address,town,description,avg_rating) values ('druga', 'AdresaKompanije','Beograd', 'OpisKompanije', 4.5);
insert into appointments (company_id,admin_full_name,duration,date_and_time) values (1,'ImePrezimeAdmina', '1 hour', '2024-01-03 14:30:00');
commit;
