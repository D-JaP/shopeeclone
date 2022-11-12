DROP TABLE IF EXISTS activation_token;

CREATE TABLE activation_token (
    user_id int8 NOT NULL,
    activation_token VARCHAR(255),
    expiration_date TIMESTAMP,

    PRIMARY KEY (user_id)

);