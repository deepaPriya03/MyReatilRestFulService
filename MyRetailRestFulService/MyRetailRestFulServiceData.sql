# CREATE Query to create a Schema with name TargetExercise
CREATE SCHEMA `TargetExercise`;

# INSERT Query to insert 5 test records into DB
INSERT INTO `TargetExercise`.`Products` (`id`, `name`, `value`, `currency_code`) VALUES ('15117729', 'Apple iPhone 7 128GB', '799.99', 'USD');
INSERT INTO `TargetExercise`.`Products` (`id`, `name`, `value`, `currency_code`) VALUES ('16483589', 'Apple Macbook Pro 13.3\"', '1099.99', 'USD');
INSERT INTO `TargetExercise`.`Products` (`id`, `name`, `value`, `currency_code`) VALUES ('16696652', 'Xbox 360 1TB', '249.50', 'USD');
INSERT INTO `TargetExercise`.`Products` (`id`, `name`, `value`, `currency_code`) VALUES ('16752456', 'Dell Inspiron 15.3\"', '1299.99', 'USD');
INSERT INTO `TargetExercise`.`Products` (`id`, `name`, `value`, `currency_code`) VALUES ('15643793', 'Apple Watch Series 2', '699.99', 'USD');
INSERT INTO `TargetExercise`.`Products` (`id`, `name`, `value`, `currency_code`) VALUES ('13860428', 'The Big Lebowski (Blu-ray)', '559.00', 'USD');

# SELECT Query To Fetch Inserted Records 
SELECT * FROM TargetExercise.Products;


