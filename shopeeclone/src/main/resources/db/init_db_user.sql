DROP SEQUENCE IF EXISTS user_id_seq;
DROP TABLE IF EXISTS users;

CREATE SEQUENCE user_id_seq  START 1 INCREMENT 1;

CREATE TABLE users (
    id int8 NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    user_role VARCHAR(10),
    phone_number VARCHAR(255),
    gender VARCHAR(10),
    date_of_birth DATE,
    postcode INT,
    address VARCHAR(255),
    city VARCHAR(255),

    PRIMARY KEY (id)
);
