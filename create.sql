CREATE TABLE admin
(
    id INTEGER PRIMARY KEY NOT NULL,
    login CHAR(30) NOT NULL,
    password CHAR(50) NOT NULL,
    name CHAR(30),
    surname CHAR(50)
);
CREATE TABLE player
(
    id INTEGER PRIMARY KEY NOT NULL,
    login CHAR(30) NOT NULL,
    password CHAR(50) NOT NULL,
    name CHAR(30),
    surname CHAR(50),
    score INTEGER NOT NULL
);
CREATE TABLE questions (
    id    INTEGER NOT NULL,
    cat   TEXT NOT NULL,
    content       TEXT NOT NULL,
    ans0  TEXT NOT NULL,
    ans1  TEXT NOT NULL,
    ans2  TEXT NOT NULL,
    ans3  TEXT NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE proposedQuestions (
    id    INTEGER NOT NULL,
    content       TEXT NOT NULL,
    cat   TEXT NOT NULL,
    ans0  TEXT NOT NULL,
    ans1  TEXT NOT NULL,
    ans2  TEXT NOT NULL,
    ans3  TEXT NOT NULL,
    PRIMARY KEY(id)
);
Insert into player(id, login, password, name, surname, score) values ('1', 'ResultSet', 'halko', 'ResultSet', 'ResultSet', '0');
Insert into admin(id, login, password, name, surname) values ('1', 'jerzyna', 'zeszczecina', 'Jerzy', 'Szczecinski');