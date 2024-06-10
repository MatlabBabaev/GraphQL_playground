DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);

insert into customer(name, age, city)
values
    ('sam', 10, 'Namangan'),
    ('Ota', 10, 'Namangan'),
    ('Jay', 12, 'Fergana'),
    ('Shax', 11, 'Samarkand');