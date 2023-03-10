DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       userId INT NOT NULL,
                       balance float NOT NULL
);

INSERT INTO users (userId, balance) VALUES
                                         (1, 100.00),
                                         (2, 200.00),
                                         (3, 500.00),
                                         (4, 1000.00),
                                         (5, 2000.00);