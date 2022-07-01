DROP TABLE IF EXISTS users, flight;

CREATE TABLE flight (
    id SERIAL PRIMARY KEY,
    from_city VARCHAR(45) NOT NULL,
    to_city VARCHAR(45) NOT NULL,
    departure TIMESTAMP NOT NULL,
    arrival TIMESTAMP NOT NULL
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone INT NOT NULL,
    flight_id INT DEFAULT NULL,
    CONSTRAINT fk_flight FOREIGN KEY(flight_id) REFERENCES flight(id) ON DELETE SET NULL
);