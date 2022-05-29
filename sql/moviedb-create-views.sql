-- List all roles and movies that each actor has starred in.
CREATE VIEW actors_in_movies AS 
SELECT
  person.fname,
  person.lname,
  movie.title
FROM person
JOIN actorship
  ON person.person_id = actorship.actor_id
JOIN movie
  ON movie.movie_id = actorship.movie_id;

-- List all movies each person has directed
CREATE VIEW directors_movies AS
SELECT person.fname, person.lname, movie.title FROM person
INNER JOIN movie 
ON movie.director_id = person.person_id;

-- For each genre, list every movie that has that genre amongst its genres
CREATE VIEW movies_in_genres AS
SELECT 
	genre.genre_name,
    movie.title
FROM genre
JOIN genreship
  ON genre.genre_name = genreship.genre_name
JOIN movie
  ON movie.movie_id = genreship.movie_id;

-- List the top 10 best movies from best to worst according to IMDB score
CREATE VIEW top_10 AS
SELECT movie.title, movie.score
FROM movie
ORDER BY movie.score DESC LIMIT 10;

-- Display the premiere date for each movie, from oldest to newest
CREATE VIEW oldest_movies AS
SELECT movie.title, premiere.premiere_date 
FROM movie
JOIN premiere
ON movie.movie_id = premiere.movie_id
ORDER BY premiere.premiere_date ASC;