USE dds_main;

DROP TABLE IF EXISTS customer_contact_info;
CREATE TABLE customer_contact_info (
customer_id INT UNIQUE NOT NULL,
country VARCHAR(30) DEFAULT '',
city VARCHAR(50) DEFAULT '',
postal_code VARCHAR(10) DEFAULT '',
street VARCHAR(100) DEFAULT '',
house_number VARCHAR(10) DEFAULT '',
telephone VARCHAR(20) DEFAULT '',
email VARCHAR(50) DEFAULT '',
FOREIGN KEY (customer_id)
	REFERENCES customer (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;