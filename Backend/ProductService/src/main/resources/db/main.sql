-- create data base
\c product_db;

-- run other sql file
\ir ./init_db/init_db_product.sql
\ir ./init_db/init_db_category.sql
\ir ./init_db/init_db_attribute.sql
\ir ./init_db/init_db_attribute_set.sql
\ir ./init_db/init_db_attribute_value.sql
\ir ./init_db/init_join_attribute_attribute_set.sql

\ir ./test_value/add_test_product.sql
\ir ./test_value/add_test_product_image.sql
\ir ./test_value/add_test_value_attribute_set.sql
\ir ./test_value/add_test_value_attribute.sql
\ir ./test_value/add_test_value_category.sql
\ir ./test_value/add_test_attribute_value.sql
\ir ./test_value/add_test_attribute_attribute_set.sql

