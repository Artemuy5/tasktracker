ALTER TABLE tracker.users CHANGE COLUMN enabled enabled BOOLEAN;

/*roles*/
INSERT INTO tracker.roles(name) VALUES('DEVELOPER');
INSERT INTO tracker.roles(name) VALUES('MANAGER');

/*statuses*/
INSERT INTO tracker.statuses(status_name) VALUES('waiting');
INSERT INTO tracker.statuses(status_name) VALUES('implementation');
INSERT INTO tracker.statuses(status_name) VALUES('verifying');
INSERT INTO tracker.statuses(status_name) VALUES('releasing');


/*projects*/
INSERT INTO tracker.projects(id, project_name) VALUES ('1', 'Modern project about nano-technologies and bla bla bla...');
INSERT INTO tracker.projects(id, project_name) VALUES ('2', 'Test project2...');
INSERT INTO tracker.projects(id, project_name) VALUES ('3', 'Test project3...');
INSERT INTO tracker.projects(id, project_name) VALUES ('4', 'Test project4...');
INSERT INTO tracker.projects(id, project_name) VALUES ('5', 'Test project5...');
INSERT INTO tracker.projects(id, project_name) VALUES ('6', 'Test project6...');
INSERT INTO tracker.projects(id, project_name) VALUES ('7', 'Test project7...');
INSERT INTO tracker.projects(id, project_name) VALUES ('8', 'Test project8...');
INSERT INTO tracker.projects(id, project_name) VALUES ('9', 'Test project9...');
INSERT INTO tracker.projects(id, project_name) VALUES ('10', 'Test project10...');
INSERT INTO tracker.projects(id, project_name) VALUES ('11', 'Test project11...');
INSERT INTO tracker.projects(id, project_name) VALUES ('12', 'Test project12...');
INSERT INTO tracker.projects(id, project_name) VALUES ('13', 'Test project13...');
INSERT INTO tracker.projects(id, project_name) VALUES ('14', 'Test project14...');
INSERT INTO tracker.projects(id, project_name) VALUES ('15', 'Test project15...');


/*tasks*/
INSERT INTO tracker.tasks(id, task_name, text, project_id, status_id, user_id)
VALUES ('1', 'Test task', 'Технологии:
	Backend - Java 8, Spring, Hibernate, MySQL (или  Node.js)
	Frontend - React.js

	Описание:
Подготовить простой таск трекер. В системе следующие сущности:
1.	User (2 роли – Developer и Manager)
2.	Project
3.	Task
4.	Comment
Реализовать возможность зарегистрироваться в системе в роли Manager или Developer с отправкой письма на почту с ссылкой активации.
Manager может(после подтверждения письма):
1.	залогиниться, используя введенные при регистрации email и password
2.	видеть список всех Project, которые он создал
3.	создавать новый Project
4.	видеть список всех тасков для выбранного проекта
5.	создавать новый Task в рамках проекта
6.	при клике на таск переходить на страничку с детальным описанием таска и комментариями
7.	искать Developer по имени и фамилии
8.	добавлять Developer в Project
9.	назначать Developer Task
10.	добавлять/удалять/редактировать Comment в рамках Task
11.	менять статус Task(доступные статусы: ‘waiting’, ‘implementation’, ‘verifying’, releasing’)
Developer может(после подтверждения письма):
1.	залогиниться, используя введенные при регистрации email и password
2.	видеть список всех проектов, в которых он участвует
3.	видеть список всех тасков для выбранного проекта
4.	Фильтровать таски: показывать таски, которые нужно выполнить только ему
5.	создавать новый Task в рамках проекта
6.	при клике на таск переходить на страничку с детальным описанием таска и комментариями
7.	добавлять/удалять/редактировать Comment в рамках Task
8.	менять статус Task(доступные статусы: ‘waiting’, ‘implementation’, ‘verifying’, releasing’)

', '1', '4', NULL);
INSERT INTO tracker.tasks(id, task_name, text, project_id, status_id, user_id)
VALUES ('2', 'Test task', 'bla bla bla', '1', '1', NULL);
INSERT INTO tracker.tasks(id, task_name, text, project_id, status_id, user_id)
VALUES ('3', 'Test task', 'bla bla bla', '1', '1', NULL);
INSERT INTO tracker.tasks(id, task_name, text, project_id, status_id, user_id)
VALUES ('4', 'Test task', 'bla bla bla', '1', '1', NULL);
INSERT INTO tracker.tasks(id, task_name, text, project_id, status_id, user_id)
VALUES ('5', 'Test task', 'bla bla bla', '1', '1', NULL);



/*comments*/
INSERT INTO tracker.comments(id, text, task_id, user_id) VALUES ('1', 'First test comment', '1', '1');


/*users*/
INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id, project_id)
VALUES('1', 'Shom90@yandex.ru', 'Artem', 'Shapovalov', '123', 'Artemuy5', '1', '2', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('2', 'Bugor@mail.ru', 'Denis', 'Bukreev', '123', 'Balamut', '1', '2');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('3', 'Fgh@mail.ru', 'Sveta', 'Selivanova', '123', 'Svedko_krevedko', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('4', 'Vbn@gmail.ru', 'Masha', 'Kislova', '123', 'Mahoho', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('5', 'Rty@yandex.ru', 'Stas', 'Sindalovski', '123', 'Sindal', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('6', 'Qwe@yandex.ru', 'Artem', 'Lisenok', '123', 'Pet', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('7', 'Asd@mail.ru', 'Vika', 'Kovalkova', '123', 'Devika', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('8', 'Pupkin@gmail.ru', 'Vasia', 'Pupkin', '123', 'Pup_ok', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('9', 'Zxc@gmail.ru', 'Tolya', 'Shatski', '123', 'Fisher', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('10', 'Rty@yandex.ru', 'Stas', 'Sindalovski', '123', 'Faster', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('11', 'Pyt@yandex.ru', 'Artem', 'Lisenok', '123', 'Gurman', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('12', 'Mnb@mail.ru', 'Vika', 'Kovalkova', '123', 'Beauty', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('13', 'Poi@gmail.ru', 'Vasia', 'Pupkin', '123', 'Wow', '1', '1');

INSERT INTO tracker.users(id, email, first_name, last_name, password, username, enabled, role_id)
VALUES('14', 'Uyt@gmail.ru', 'Tolya', 'Shatski', '123', 'Unknown', '1', '1');