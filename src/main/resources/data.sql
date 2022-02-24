insert into PUBLIC.AUTHOR (ID, NAME) values (1, 'Лев Толстой'), (2, 'Николай Гоголь'), (3, 'Жуль Верн'), (4, 'Стивен Кинг');
insert into PUBLIC.GENRE (ID, NAME) values (1, 'Драма'), (2, 'Комедия'), (3, 'Фантастика'), (4, 'Ужас');

insert into PUBLIC.BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (1, 'Власть тьмы', 1, 1);
insert into PUBLIC.BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (2, 'Власть тьмы2', 2, 3);

insert into PUBLIC.COMMENTS(ID, CONTENT, BOOK_ID) values (1, 'Это про Буратино книжка', 1);
insert into PUBLIC.COMMENTS(ID, CONTENT, BOOK_ID) values (2, 'Это про Старика Хоттабыча книжка', 3);