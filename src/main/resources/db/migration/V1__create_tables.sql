CREATE TABLE user (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      username VARCHAR(100),
      email VARCHAR(150) UNIQUE,
      password VARCHAR(255)
);