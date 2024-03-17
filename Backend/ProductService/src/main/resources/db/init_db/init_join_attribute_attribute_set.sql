DROP TABLE IF EXISTS attribute_attribute_set;

CREATE TABLE attribute_attribute_set(
    attribute_id int not null,
    attribute_set_id int not null,

    primary key (attribute_id, attribute_set_id)
)