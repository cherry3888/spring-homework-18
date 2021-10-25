INSERT INTO AUTHOR (id, name) values (1, 'Author-1');
INSERT INTO AUTHOR (id, name) values (2, 'Author-2');
INSERT INTO AUTHOR (id, name) values (3,'Author-3');

INSERT INTO GENRE (id, name) values (1, 'Genre-1');
INSERT INTO GENRE (id, name) values (2, 'Genre-2');
INSERT INTO GENRE (id, name) values (3, 'Genre-3');

INSERT INTO BOOK (title, author_id, genre_id) values ('Book-1', 1, 1);
INSERT INTO BOOK (title, author_id, genre_id) values ('Book-2', 1, 2);
INSERT INTO BOOK (title, author_id, genre_id) values ('Book-3', 1, 3);

INSERT INTO COMMENT (book_id, content) values (1, 'Good');
INSERT INTO COMMENT (book_id, content) values (1, 'Bad');

