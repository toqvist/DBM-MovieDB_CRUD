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
call sp_add_movie('Pulp Fiction', 154, 8.9, 'R', 'Quentin', 'Tarantino');
call sp_add_movie('Django Unchained', 165, 8.4, 'R', 'Quentin', 'Tarantino');
call sp_add_movie('Inglorious Basterds', 153, 8.3, 'R', 'Quentin', 'Tarantino');
call sp_add_movie('Inception', 148, 8.8, 'PG-13', 'Christopher', 'Nolan');
call sp_add_movie('The Dark Knight', 152, 9.0, 'PG-13', 'Christopher', 'Nolan');
call sp_add_movie('The Dark Knight Rises', 164, 8.5, 'PG-13', 'Christopher', 'Nolan');
call sp_add_movie('Fight Club', 120, 8.8, 'R', 'David', 'Fincher' );
call sp_add_movie('Goodfellas', 145, 8.7, 'R', 'Martin', 'Scorsese');
call sp_add_movie('The Lord of the Rings: The Fellowship of the Ring', 178, 8.8, 'PG-13', 'Peter', 'Jackson');
call sp_add_movie('The Lord of the Rings: The Two Towers', 179, 8.8, 'PG-13', 'Peter', 'Jackson');

-- Add actorships
call sp_add_actorship('Samuel', 'Jackson', 'Pulp Fiction', 'Jules Winnifield');
call sp_add_actorship('Samuel', 'Jackson', 'Django Unchained', 'Stephen');
call sp_add_actorship('Samuel', 'Jackson', 'Inglorious Basterds', 'Narrator');
call sp_add_actorship('Quentin', 'Tarantino', 'Django Unchained', 'Robert (Bag Head)');
call sp_add_actorship('John', 'Travolta', 'Pulp Fiction', 'Vincent Vega');
call sp_add_actorship('Brad', 'Pitt', 'Fight Club', 'Tyler Durden');
call sp_add_actorship('Brad', 'Pitt', 'Inglorious Basterds', 'L.t. Aldo Raine');
call sp_add_actorship('Leonardo', 'DiCaprio', 'Django Unchained', 'Calvin Candie');
call sp_add_actorship('Cristoph', 'Waltz', 'Django Unchained', 'Dr. King Schultz');
call sp_add_actorship('Robert', 'De Niro', 'Goodfellas', 'James Conway');

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

INSERT INTO genreship(movie_id, genre_name)
VALUES(3, 'Drama'),
(3, 'War'),
(3, 'Adventure'),
(9, 'Fantasy'),
(9, 'Adventure'),
(9, 'Action'),
(9, 'Drama'),
(4, 'Action'),
(4, 'Sci-Fi'),
(8, 'Crime');

INSERT INTO premiere(movie_id, premiere_date, country)
VALUES(1, '1995-10-14', 'US'),
(2, '2012-12-25', 'US'),
(3, '2009-08-21', 'US'),
(4, '2010-07-16', 'US'),
(5, '2008-07-18', 'US'),
(6, '2012-07-20', 'US'),
(7, '1999-10-15', 'US'),
(8, '1990-11-21', 'US'),
(9, '2001-12-19', 'US'),
(10, '2002-12-18', 'US');


