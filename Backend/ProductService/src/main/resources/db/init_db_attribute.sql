DROP TABLE IF EXISTS attribute;

CREATE TABLE attribute (
    attribute_id serial not null ,
    name varchar not null ,
    datatype varchar not null ,
    is_require bool,
    default_value varchar,
    primary key (attribute_id)
);