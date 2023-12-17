DROP TABLE IF EXISTS attribute CASCADE ;

CREATE TABLE attribute (
    attribute_id serial not null ,
    name varchar not null ,
    datatype varchar not null ,
    is_require bool,
    default_value varchar,
    primary key (attribute_id)
);


INSERT INTO attribute (name, datatype, is_require, default_value) VALUES
                                                                      ('Material', 'VARCHAR', false, 'Not Specified'),
                                                                      ('Rating', 'INTEGER', false, '0'),
                                                                      ('ISBN', 'VARCHAR', true, null),
                                                                      ('Resolution', 'VARCHAR', false, null),
                                                                      ('Processor', 'VARCHAR', false, 'Unknown'),
                                                                      ('RAM', 'VARCHAR', false, '4GB'),
                                                                      ('Genre', 'VARCHAR', true, 'Not Specified'),
                                                                      ('ReleaseYear', 'INTEGER', false, null),
                                                                      ('ScreenSize', 'FLOAT', false, null),
                                                                      ('StorageCapacity', 'VARCHAR', false, '128GB'),
                                                                      ('Color', 'VARCHAR', true, null),
                                                                      ('Weight', 'FLOAT', false, null),
                                                                      ('Brand', 'VARCHAR', false, 'Generic'),
                                                                      ('Author', 'VARCHAR', true, 'Unknown'),
                                                                      ('Size', 'VARCHAR', true, null),
                                                                      ('Language', 'VARCHAR', false, 'English'),
                                                                      ('Pages', 'INTEGER', false, null),
                                                                      ('Manufacturer', 'VARCHAR', false, null),
                                                                      ('Power', 'VARCHAR', false, null),
                                                                      ('Condition', 'VARCHAR', true, 'New');