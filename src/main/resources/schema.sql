DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID BIGINT PRIMARY KEY auto_increment, NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID BIGINT PRIMARY KEY auto_increment, NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
    ID BIGINT PRIMARY KEY auto_increment,
    NAME VARCHAR(255),
    AUTHOR_ID BIGINT REFERENCES AUTHOR(ID),
    GENRE_ID BIGINT REFERENCES GENRE(ID),
    foreign key (GENRE_ID) references GENRE(id) on delete cascade,
    foreign key (AUTHOR_ID) references AUTHOR(id) on delete cascade);

DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT(
     ID BIGINT PRIMARY KEY auto_increment,
     CONTENT VARCHAR(255) NOT NULL,
     BOOK_ID BIGINT REFERENCES BOOKS(ID),
     FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID) on update cascade on delete cascade
);