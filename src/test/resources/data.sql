insert into PUBLIC.AUTHOR (ID, NAME) values (1, 'Лев Толстой'), (2, 'Николай Гоголь');
insert into PUBLIC.GENRE (ID, NAME) values (1, 'Драма'), (2, 'Комедия');

insert into PUBLIC.BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('Власть тьмы', '2', '2');
insert into PUBLIC.BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('Lets do it', '2', '2');
