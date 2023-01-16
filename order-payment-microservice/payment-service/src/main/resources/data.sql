DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       balance float NOT NULL
);

INSERT INTO users (balance)
VALUES  (100.00),
        (200.00),
        (500.00),
        (1000.00),
        (2000.00);