DROP TABLE IF EXISTS product;

CREATE TABLE product (
    product_id serial not null,
    name varchar not null,
    price float,
    category_id int,
    description text,
    seller int,
    primary key (product_id)
);

ALTER TABLE product ADD COLUMN "seller" int;
ALTER TABLE product ADD COLUMN "attribute_set_id" int;

CREATE TABLE product_image (
    image_id serial not null,
    product_id int not null ,
    image_url varchar not null,
    primary key (image_id),
    foreign key (product_id) references product
);

ALTER TABLE product_image ADD COLUMN "img_order" int;

CREATE SEQUENCE product_id_seq;
CREATE SEQUENCE product_image_id_seq;

