
create table authors(
    id integer primary key,
    name varchar(50),
    age integer
);
create table books(
    isbn varchar(50) primary key ,
    tittle varchar(50),
    authorId integer
);

alter table books
add foreign key (authorId)
references authors(id);