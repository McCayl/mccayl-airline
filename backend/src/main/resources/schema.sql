DROP TABLE IF EXISTS flight, passenger, users, role, users_role;
CREATE EXTENSION IF NOT EXISTS CITEXT;

CREATE TABLE flight (
    id SERIAL PRIMARY KEY,
    from_city VARCHAR(45) NOT NULL,
    to_city VARCHAR(45) NOT NULL,
    departure TIMESTAMP NOT NULL,
    arrival TIMESTAMP NOT NULL
);

CREATE TABLE passenger (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(45) NOT NULL,
    lastname VARCHAR(45) NOT NULL,
    pass_id INT NOT NULL,
    email CITEXT NOT NULL,
    flight_id INT DEFAULT NULL,
    CONSTRAINT fk_flight FOREIGN KEY(flight_id) REFERENCES flight(id) ON DELETE SET NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(120) NOT NULL
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE users_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES role(id)
);