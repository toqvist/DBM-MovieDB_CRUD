-- Add actors
INSERT INTO person (fname, lname, birth_date)
VALUES ('Quentin', 'Tarantino', '1963-03-27'),
('Samuel','Jackson', '1948-12-21'),
('John', 'Travolta', '1954-02-18'),
('Cristoph', 'Waltz', '1956-04-01'),
('Brad', 'Pitt', '1963-12-18'),
('Robert', 'De Niro', '1943-09-19'),
('Morgan', 'Freeman', '1937-09-06'),
('Leonardo', 'DiCaprio', '1974-11-11'),
('Christopher', 'Nolan', '1970-06-30'),
('Tom', 'Hardy', '1948-07-31'),
('Robert', 'Downey Jr.', '1969-04-04'),
('David', 'Fincher', '1962-08-28'),
('Martin', 'Scorsese', '1942-11-17'),
('Peter', 'Jackson', '1961-10-31');

-- Add movies
CALL sp_add_movie('Pulp Fiction', 154, 8.9, 'R', 'Quentin', 'Tarantino');
CALL sp_add_movie('Django Unchained', 165, 8.4, 'R', 'Quentin', 'Tarantino');
CALL sp_add_movie('Inglorious Basterds', 153, 8.3, 'R', 'Quentin', 'Tarantino');
CALL sp_add_movie('Inception', 148, 8.8, 'PG-13', 'Christopher', 'Nolan');
CALL sp_add_movie('The Dark Knight', 152, 9.0, 'PG-13', 'Christopher', 'Nolan');
CALL sp_add_movie('The Dark Knight Rises', 164, 8.5, 'PG-13', 'Christopher', 'Nolan');
CALL sp_add_movie('Fight Club', 120, 8.8, 'R', 'David', 'Fincher' );
CALL sp_add_movie('Goodfellas', 145, 8.7, 'R', 'Martin', 'Scorsese');
CALL sp_add_movie('The Lord of the Rings: The Fellowship of the Ring', 178, 8.8, 'PG-13', 'Peter', 'Jackson');
CALL sp_add_movie('The Lord of the Rings: The Two Towers', 179, 8.8, 'PG-13', 'Peter', 'Jackson');

-- Add actorships
CALL sp_add_actorship('Samuel', 'Jackson', 'Pulp Fiction', 'Jules Winnifield');
CALL sp_add_actorship('Samuel', 'Jackson', 'Django Unchained', 'Stephen');
CALL sp_add_actorship('Samuel', 'Jackson', 'Inglorious Basterds', 'Narrator');
CALL sp_add_actorship('Quentin', 'Tarantino', 'Django Unchained', 'Robert (Bag Head)');
CALL sp_add_actorship('John', 'Travolta', 'Pulp Fiction', 'Vincent Vega');
CALL sp_add_actorship('Brad', 'Pitt', 'Fight Club', 'Tyler Durden');
CALL sp_add_actorship('Brad', 'Pitt', 'Inglorious Basterds', 'L.t. Aldo Raine');
CALL sp_add_actorship('Leonardo', 'DiCaprio', 'Django Unchained', 'Calvin Candie');
CALL sp_add_actorship('Cristoph', 'Waltz', 'Django Unchained', 'Dr. King Schultz');
CALL sp_add_actorship('Robert', 'De Niro', 'Goodfellas', 'James Conway');

INSERT INTO genre(genre_name) 
VALUES
('Crime'),
('Thriller'),
('Drama'),
('Comedy'),
('Biography'),
('Action'),
('Sci-Fi'),
('Adventure'),
('Fantasy'),
('War');


call sp_add_genreship('Inglorious Basterds', 'Drama');
call sp_add_genreship('Inglorious Basterds', 'War');
call sp_add_genreship('Inglorious Basterds', 'Adventure');
call sp_add_genreship('The Lord of the Rings: The Fellowship of the Ring', 'Fantasy');
call sp_add_genreship('The Lord of the Rings: The Fellowship of the Ring', 'Adventure');
call sp_add_genreship('The Lord of the Rings: The Fellowship of the Ring', 'Action');
call sp_add_genreship('The Lord of the Rings: The Fellowship of the Ring', 'Drama');
call sp_add_genreship('Inception', 'Action');
call sp_add_genreship('Inception', 'Sci-Fi');
call sp_add_genreship('Goodfellas', 'Crime');

-- Add premieres
call sp_add_premiere('Pulp Fiction', '1995-10-14', 'US');
call sp_add_premiere('Django Unchained', '2012-12-25', 'US');
call sp_add_premiere('Inglorious Basterds', '2009-08-21', 'US');
call sp_add_premiere('Inception', '2010-07-16', 'US');
call sp_add_premiere('The Dark Knight', '2008-07-18', 'US');
call sp_add_premiere('The Dark Knight Rises', '2012-07-20', 'US');
call sp_add_premiere('Fight Club', '1999-10-15', 'US');
call sp_add_premiere('Goodfellas', '1990-11-21', 'US');
call sp_add_premiere('The Lord of the Rings: The Fellowship of the Ring', '2001-12-19', 'US');
call sp_add_premiere('The Lord of the Rings: The Two Towers', '2002-12-18', 'US');


