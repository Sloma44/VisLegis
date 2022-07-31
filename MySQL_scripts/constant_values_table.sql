USE dds_main;

DROP TABLE IF EXISTS constant_values;

CREATE TABLE constant_values (
id INT PRIMARY KEY AUTO_INCREMENT,
value_name VARCHAR(100) DEFAULT '',
const_value DECIMAL(8,2)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;