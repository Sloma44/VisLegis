USE dds_main;

DROP TABLE IF EXISTS court_registry;

CREATE TABLE IF NOT EXISTS court_registry (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(75),
department VARCHAR(75),
street_and_number VARCHAR(75),
postal_and_city VARCHAR(75)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;