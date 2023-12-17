DROP TABLE IF EXISTS attribute_value CASCADE ;

CREATE TABLE attribute_value(
    attribute_value_id serial not null ,
    product_id int not null ,
    attribute_id int not null ,
    type varchar not null ,
    value varchar,
    seller_id int,

    primary key (attribute_value_id),
    foreign key (attribute_id) references attribute,
    foreign key (product_id) references product
);

CREATE SEQUENCE attribute_id_seq;