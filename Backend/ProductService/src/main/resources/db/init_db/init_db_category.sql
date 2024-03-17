DROP TABLE IF EXISTS category;

CREATE TABLE category (
    category_id serial ,
    name varchar not null ,
    level int8 not null,
    parent_id int,

--     key      ---
    primary key (category_id)
);