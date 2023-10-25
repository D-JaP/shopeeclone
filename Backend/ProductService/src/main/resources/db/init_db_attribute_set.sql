DROP TABLE IF EXISTS attribute_set;

CREATE TABLE attribute_set(
    attribute_set_id serial not null ,
    name varchar not null,

    primary key (attribute_set_id)
)