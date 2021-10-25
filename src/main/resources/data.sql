INSERT INTO AUTHOR (id, name) values (1, 'Достоевский');
INSERT INTO AUTHOR (id, name) values (2, 'Тургенев');
INSERT INTO AUTHOR (id, name) values (3,'Чехов');

INSERT INTO GENRE (id, name) values (1, 'Детектив');
INSERT INTO GENRE (id, name) values (2, 'Роман');
INSERT INTO GENRE (id, name) values (3, 'Приключения');
INSERT INTO GENRE (id, name) values (4, 'Пьесса');

INSERT INTO BOOK (title, author_id, genre_id) values ('Братья Карамазовы', 1, 1);
INSERT INTO BOOK (title, author_id, genre_id) values ('Преступление и наказание', 1, 2);
INSERT INTO BOOK (title, author_id, genre_id) values ('Идиот', 1, 3);
INSERT INTO BOOK (title, author_id, genre_id) values ('Отцы и дети', 2, 1);
INSERT INTO BOOK (title, author_id, genre_id) values ('Му-Му', 2, 2);
INSERT INTO BOOK (title, author_id, genre_id) values ('Записки охотника', 2, 3);
INSERT INTO BOOK (title, author_id, genre_id) values ('Чайка', 3, 4);
INSERT INTO BOOK (title, author_id, genre_id) values ('Три сестры', 3, 2);
INSERT INTO BOOK (title, author_id, genre_id) values ('Каштанка', 3, 2);

INSERT INTO COMMENT (book_id, content) values (1, 'Хорошая');
INSERT INTO COMMENT (book_id, content) values (1, 'Плохая');