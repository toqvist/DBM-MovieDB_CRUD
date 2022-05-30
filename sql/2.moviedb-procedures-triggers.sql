DELIMITER // 

-- ADD MOVIE
CREATE PROCEDURE sp_add_movie (
    IN movie_title varchar(100),
    IN movie_runtime FLOAT,
    IN movie_score FLOAT,
    IN movie_rating varchar(5),
    IN director_fname varchar(50),
    IN director_lname varchar(50)
) 
BEGIN
INSERT INTO movie (title, runtime, score, rating, director_id)
VALUES(
    movie_title,
    movie_runtime,
    movie_score,
    movie_rating,
    (SELECT person_id 
        FROM person 
        WHERE (director_fname = person.fname 
            AND director_lname = person.lname) 
        LIMIT 1
    )
);

END //

-- ADD ACTORSHIP
CREATE PROCEDURE sp_add_actorship (
    IN actor_fname VARCHAR(50),
    IN actor_lname VARCHAR(50),
    IN movie_title VARCHAR(100),
    IN p_character_role VARCHAR(50)
)

BEGIN
INSERT INTO actorship (actor_id, movie_id, character_role)
VALUES(
    (SELECT person_id 
        FROM person 
        WHERE (actor_fname = person.fname 
            AND actor_lname = person.lname) 
        LIMIT 1
    ),
    (SELECT movie_id 
        FROM movie
        WHERE (movie_title = movie.title)
        LIMIT 1
    ),
    p_character_role
);

END //

-- ADD GENRESHIP
CREATE PROCEDURE sp_add_genreship (
    IN movie_title VARCHAR(100),
    IN genre VARCHAR(30)
)

BEGIN
INSERT INTO genreship (
    movie_id,
    genre_name
)
VALUES(
    (SELECT movie_id 
        FROM movie
        WHERE (movie_title = movie.title)
        LIMIT 1
    ),
    (SELECT genre_name 
        FROM genre
        WHERE (genre = genre.genre_name)
        LIMIT 1
    )
);

END //

-- ADD PREMIERE
CREATE PROCEDURE sp_add_premiere (
    IN p_movie_title VARCHAR(100),
    IN p_premiere_date date,
    IN p_country VARCHAR(100)
)

BEGIN
INSERT INTO premiere (
    movie_id,
    premiere_date,
    country
)
VALUES(
    (SELECT movie_id 
        FROM movie
        WHERE (p_movie_title = movie.title)
        LIMIT 1
    ),
    p_premiere_date,
    p_country
);

END //


CREATE TRIGGER person_check_duplicate
BEFORE INSERT ON person
FOR EACH ROW
BEGIN
  IF (EXISTS(SELECT 1 FROM person 
  WHERE (
      person.fname = new.fname
  AND person.lname = new.lname
  AND person.birth_date = new.birth_date
  )))
  THEN 
    SIGNAL SQLSTATE VALUE '45000' SET MESSAGE_TEXT = 'Query failed: Duplicate person entry.';

  END IF;
END //


DELIMITER ;



