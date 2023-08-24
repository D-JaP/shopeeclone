DROP SEQUENCE IF EXISTS user_id_seq;
DROP TABLE IF EXISTS users;
CREATE SEQUENCE user_id_seq  START 2 INCREMENT 1;
CREATE TABLE users (
    id int8 NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    user_role VARCHAR(10),
    is_active VARCHAR(5),
    provider VARCHAR(255),
    firstname varchar(30),
    lastname varchar(30),
    PRIMARY KEY (id)
    );
