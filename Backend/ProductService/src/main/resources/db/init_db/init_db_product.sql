DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE product (
    product_id serial not null,
    name varchar not null,
    price float,
    category_id int,
    description text,
    seller int,
    primary key (product_id)
);
ALTER TABLE product ADD COLUMN "attribute_set_id" int;
ALTER TABLE product ADD COLUMN "quantity" int;

-- table prduct_image
DROP TABLE IF EXISTS product_image;
CREATE TABLE product_image (
    image_id serial not null,
    product_id int not null ,
    image_url varchar not null,
    primary key (image_id),
    foreign key (product_id) references product on delete cascade
);

ALTER TABLE product_image ADD COLUMN "img_order" int;


DROP SEQUENCE IF EXISTS product_id_seq;
DROP SEQUENCE IF EXISTS product_image_id_seq;

CREATE SEQUENCE product_id_seq start 101;
CREATE SEQUENCE product_image_id_seq start 801;

