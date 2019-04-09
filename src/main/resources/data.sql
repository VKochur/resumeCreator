insert into summary (id, career_target, date_of_born, name, other_info, skype_login, url_avatar)values (1, 'Java developer', '01.01.1990', 'Петров Петр Петрович','Английский со словарем', 'skypePetrov', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhCUtiRypWBfPDnQT3k6c8BLd1aaBhN5XATtYPyjaPR_CeEPvS');
insert into summary (id, career_target, date_of_born, name, other_info, skype_login, url_avatar) values (2, 'C++ developer', '01.01.1985', 'Константинов А.В.','Хобби: выращивание кактусов', 'konst85', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXNUFujGCV6hdkAqfugWOSIvoz6wP1puhhxs1qzur08zV1TqNovA');
insert into summary (id, career_target, date_of_born, name, other_info, skype_login, url_avatar) values (3, 'Java junior', '01.01.1990', 'Маркелов Иван','Аналитический слад ума, желание развиваться', 'markelov', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMZmyeLfqMsi1Bi2lE6mviJxPRMlzicNTDoT5LLTCXs30Wi04N');
insert into summary (id, career_target, date_of_born, name, other_info, skype_login, url_avatar) values (4, 'PR менеджер', '01.01.1982', 'Иванова Елена Максимовна',null, 'ivanova', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6UulFHctAIfMRGHxoVPzJeLfu6Z0bfZ5IJLGtWwLJ2zkTtg2ueA');

insert into phones (resume_id, phone_numbers) values (1, '888-888-88');
insert into phones (resume_id, phone_numbers) values (1, '888-777-88');
insert into phones (resume_id, phone_numbers) values (2, '5555-5555');
insert into phones (resume_id, phone_numbers) values (3, '8-90-943-3242');
insert into phones (resume_id, phone_numbers) values (4, '8-90-0000 -242');

insert into emails (resume_id, emails) values (1, 'email1');
insert into emails (resume_id, emails) values (1, 'email2_1@gmail.com');
insert into emails (resume_id, emails) values (2, 'email2_2@rambler.ru');
insert into emails (resume_id, emails) values (3, 'email3');
insert into emails (resume_id, emails) values (4, 'e-mail4');

insert into basic_educations (resume_id, basic_educations) values (1, 'УлГТУ 2012г. ');
insert into basic_educations (resume_id, basic_educations) values (1, 'МГУ 2017г. Инженер программист');
insert into basic_educations (resume_id, basic_educations) values (2, 'Университет экономики. Бухгалтер');
insert into basic_educations (resume_id, basic_educations) values (3, 'Университет. Год выпуска. Специальность');
insert into basic_educations (resume_id, basic_educations) values (4, null);

--insert into additional_educations (resume_id, additional_educations) values (1, 'IT курсы');
--insert into additional_educations (resume_id, additional_educations) values (1, 'Курсы английского');
--insert into additional_educations (resume_id, additional_educations) values (1, 'MBA');
--insert into additional_educations (resume_id, additional_educations) values (2, 'Курсы');
--insert into additional_educations (resume_id, additional_educations) values (3, 'Курсы');
--insert into additional_educations (resume_id, additional_educations) values (4, 'Курсы');

insert into experiences (resume_id, experiences) values (1, 'Инженер программист. Фирма. С 2015 по н.в.');
insert into experiences (resume_id, experiences) values (1, 'Программист. Предпрятие с 2012-2015гг.');
insert into experiences (resume_id, experiences) values (1, 'Лаборант');
insert into experiences (resume_id, experiences) values (2, 'Программист');
insert into experiences (resume_id, experiences) values (3, 'Преподаватель математики. ВУЗ');
insert into experiences (resume_id, experiences) values (4, 'Бухгалтер');

insert into targets (resume_id, targets) values (1, 'Устройство на работу');
insert into targets (resume_id, targets) values (1, 'Прохождение обучения');
insert into targets (resume_id, targets) values (2, 'Устройство на работу');
insert into targets (resume_id, targets) values (3, 'Устройство на работу');
insert into targets (resume_id, targets) values (4, 'Устройство на работу');

insert into resume_skills (resume_id, skills, skills_key) values (1, 25, 'java');
insert into resume_skills (resume_id, skills, skills_key) values (1, 10, 'c++');
insert into resume_skills (resume_id, skills, skills_key) values (1, 36, 'sql');
insert into resume_skills (resume_id, skills, skills_key) values (1, 6, 'spring');
insert into resume_skills (resume_id, skills, skills_key) values (1, 12, 'IIdea');
insert into resume_skills (resume_id, skills, skills_key) values (2, 25, 'c++');
insert into resume_skills (resume_id, skills, skills_key) values (3, 6, 'java core');
insert into resume_skills (resume_id, skills, skills_key) values (4, 12, '1C');
