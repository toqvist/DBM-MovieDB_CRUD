DELIMITER // 

CREATE PROCEDURE sp_add_movie(
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
    (SELECT person_id FROM person WHERE (director_fname = person.fname AND director_lname = person.lname) LIMIT 1)
);

END //
DELIMITER;