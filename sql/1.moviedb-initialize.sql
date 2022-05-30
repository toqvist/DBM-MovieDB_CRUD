DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE IF NOT EXISTS moviedb;
USE moviedb;


CREATE TABLE IF NOT EXISTS person (
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    birth_date date NOT NULL
);

CREATE TABLE IF NOT EXISTS movie (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    runtime FLOAT NOT NULL,
    score FLOAT,
    rating VARCHAR(5) NOT NULL,
    director_id INT NOT NULL,
    FOREIGN KEY (director_id) REFERENCES person(person_id)
);

CREATE TABLE IF NOT EXISTS premiere (
    country VARCHAR(100) NOT NULL,
    premiere_date date NOT NULL,
    movie_id INT NOT NULL,
    PRIMARY KEY (movie_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);

CREATE TABLE IF NOT EXISTS actorship (
    character_role VARCHAR(50) NOT NULL,
    actor_id INT NOT NULL,
    movie_id INT NOT NULL,
    PRIMARY KEY (actor_id, movie_id),
    FOREIGN KEY (actor_id) REFERENCES person(person_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id)
);

CREATE TABLE IF NOT EXISTS genre (
	genre_name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS genreship (
	movie_id INT NOT NULL,
    genre_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (movie_id, genre_name),
	FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
	FOREIGN KEY (genre_name) REFERENCES genre(genre_name)
);
