insert into studyplans (title, description) values ('title 1','description 1');
insert into studyplans (title, description) values ('title 2','description 2');
insert into studyplans (title, description) values ('title 3','description 3');
insert into studyplans (title, description) values ('title 4','description 4');

insert into subjects (`name`, `parent_subject_id`) values ('Subject 1',null);
insert into subjects (`name`, `parent_subject_id`) values ('Subject 2',null);
insert into subjects (`name`, `parent_subject_id`) values ('Subject 3',null);
insert into subjects (`name`, `parent_subject_id`) values ('Subject 4',null);
insert into subjects (`name`, `parent_subject_id`) values ('Subject 5',null);
insert into subjects (`name`, `parent_subject_id`) values ('Subject 6',null);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');