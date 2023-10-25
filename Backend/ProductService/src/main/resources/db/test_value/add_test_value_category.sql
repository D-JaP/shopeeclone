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


