drop table if exists refresh_token;
create table refresh_token(
    user_id  int8 not null,
    token VARCHAR(2048),
    expired TIMESTAMP,

    PRIMARY KEY (user_id)
)
