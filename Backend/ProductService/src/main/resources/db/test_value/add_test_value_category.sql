INSERT INTO category(name, level) values ('Electronics & Technology', 1 );

INSERT INTO category(name, level, parent_id) values ('Computers & Laptops', 2, (select category_id from category where name ='Electronics & Technology') );
INSERT INTO category(name, level, parent_id) values ('Smartphones', 3, (select category_id from category where name ='Computers & Laptops') );
INSERT INTO category(name, level, parent_id) values ('Phone Cases & Covers', 3, (select category_id from category where name ='Computers & Laptops') );
INSERT INTO category(name, level, parent_id) values ('Screen Protectors', 3, (select category_id from category where name ='Computers & Laptops') );


INSERT INTO category(name, level, parent_id) values ('Audio & Headphones', 2, (select category_id from category where name ='Electronics & Technology') );
INSERT INTO category(name, level, parent_id) values ('Headphones & Earphones', 3, (select category_id from category where name ='Audio & Headphones') );
INSERT INTO category(name, level, parent_id) values ('Bluetooth & Wireless Speakers', 3, (select category_id from category where name ='Audio & Headphones') );
INSERT INTO category(name, level, parent_id) values ('Home Audio Systems', 3, (select category_id from category where name ='Audio & Headphones') );

INSERT INTO category(name, level, parent_id) values ('Cameras & Photography', 2, (select category_id from category where name ='Electronics & Technology') );
INSERT INTO category(name, level, parent_id) values ('Digital Cameras', 2, (select category_id from category where name ='Cameras & Photography') );
INSERT INTO category(name, level, parent_id) values ('Camera Lenses', 2, (select category_id from category where name ='Cameras & Photography') );
INSERT INTO category(name, level, parent_id) values ('Camera Accessories', 2, (select category_id from category where name ='Cameras & Photography') );

-- Insert Smart Home Devices and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Smart Home Devices', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Smart Lighting', 3, (SELECT category_id FROM category WHERE name ='Smart Home Devices'));

INSERT INTO category (name, level, parent_id)
VALUES ('Smart Thermostats', 3, (SELECT category_id FROM category WHERE name ='Smart Home Devices'));

INSERT INTO category (name, level, parent_id)
VALUES ('Security Cameras', 3, (SELECT category_id FROM category WHERE name ='Smart Home Devices'));

-- Insert Gaming and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Gaming', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Gaming Consoles', 3, (SELECT category_id FROM category WHERE name ='Gaming'));

INSERT INTO category (name, level, parent_id)
VALUES ('Video Games', 3, (SELECT category_id FROM category WHERE name ='Gaming'));

INSERT INTO category (name, level, parent_id)
VALUES ('Gaming Accessories', 3, (SELECT category_id FROM category WHERE name ='Gaming'));

-- Insert Appliances and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Appliances', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Kitchen Appliances', 3, (SELECT category_id FROM category WHERE name ='Appliances'));

INSERT INTO category (name, level, parent_id)
VALUES ('Home Appliances', 3, (SELECT category_id FROM category WHERE name ='Appliances'));

INSERT INTO category (name, level, parent_id)
VALUES ('Personal Care Appliances', 3, (SELECT category_id FROM category WHERE name ='Appliances'));

-- Insert Office & Stationery and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Office & Stationery', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Office Furniture', 3, (SELECT category_id FROM category WHERE name ='Office & Stationery'));

INSERT INTO category (name, level, parent_id)
VALUES ('Stationery Supplies', 3, (SELECT category_id FROM category WHERE name ='Office & Stationery'));

INSERT INTO category (name, level, parent_id)
VALUES ('Printers & Scanners', 3, (SELECT category_id FROM category WHERE name ='Office & Stationery'));

-- Insert Health & Fitness and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Health & Fitness', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Fitness Trackers', 3, (SELECT category_id FROM category WHERE name ='Health & Fitness'));

INSERT INTO category (name, level, parent_id)
VALUES ('Home Gym Equipment', 3, (SELECT category_id FROM category WHERE name ='Health & Fitness'));

INSERT INTO category (name, level, parent_id)
VALUES ('Nutrition & Supplements', 3, (SELECT category_id FROM category WHERE name ='Health & Fitness'));

-- Insert Fashion & Apparel and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Fashion & Apparel', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Men''s Clothing', 3, (SELECT category_id FROM category WHERE name ='Fashion & Apparel'));

INSERT INTO category (name, level, parent_id)
VALUES ('Women''s Clothing', 3, (SELECT category_id FROM category WHERE name ='Fashion & Apparel'));

INSERT INTO category (name, level, parent_id)
VALUES ('Shoes & Accessories', 3, (SELECT category_id FROM category WHERE name ='Fashion & Apparel'));

-- Insert Books & Literature and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Books & Literature', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Fiction', 3, (SELECT category_id FROM category WHERE name ='Books & Literature'));

INSERT INTO category (name, level, parent_id)
VALUES ('Non-Fiction', 3, (SELECT category_id FROM category WHERE name ='Books & Literature'));

INSERT INTO category (name, level, parent_id)
VALUES ('Children''s Books', 3, (SELECT category_id FROM category WHERE name ='Books & Literature'));

-- Insert Home & Furniture and its subcategories
INSERT INTO category (name, level, parent_id)
VALUES ('Home & Furniture', 2, 15);

INSERT INTO category (name, level, parent_id)
VALUES ('Living Room Furniture', 3, (SELECT category_id FROM category WHERE name ='Home & Furniture'));

INSERT INTO category (name, level, parent_id)
VALUES ('Bedroom Furniture', 3, (SELECT category_id FROM category WHERE name ='Home & Furniture'));

INSERT INTO category (name, level, parent_id)
VALUES ('Home Decor', 3, (SELECT category_id FROM category WHERE name ='Home & Furniture'));



