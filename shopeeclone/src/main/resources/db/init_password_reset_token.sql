DROP TABLE IF EXISTS password_reset_token;

CREATE TABLE password_reset_token (
                                  user_id int8 NOT NULL,
                                  password_reset_token VARCHAR(255),
                                  expiration_date TIMESTAMP,

                                  PRIMARY KEY (user_id)
);