CREATE TABLE if not exists Person (
                        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        fullName varchar(100) NOT NULL UNIQUE,
                        birthday date NOT NULL default current_date,
                        constraint uk_person unique (fullName, birthday)
);

CREATE TABLE if not exists Book (
                      id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      title varchar(100) NOT NULL,
                      author varchar(100) NOT NULL,
                      year date NOT NULL default current_date,
                      person_id int REFERENCES Person(id) ON DELETE SET NULL default null,
                      constraint uk_book unique (title, author)
);

insert into Person(fullName, birthday) VALUES('Biktagirov Aynur T', '2000-10-15');
insert into Person(fullName, birthday) VALUES('Biktagirov Bulat T', '1997-10-04');

insert into Book(title, author, year, person_id) values ('Mumu', 'Turgenev', '1997-10-04', 1);

select * from Person;
select * from Book;

drop table Book;

drop table Person
