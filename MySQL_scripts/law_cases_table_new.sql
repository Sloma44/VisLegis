USE dds_main;

DROP TABLE IF EXISTS law_case;
CREATE TABLE law_case (
case_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
signature VARCHAR(15) DEFAULT '',
case_description VARCHAR(1000) DEFAULT '',
authority_id INT,
sort SET ('Z urzędu', 'Z wyboru'),
customer_id INT,
FOREIGN KEY (customer_id)
	REFERENCES customer (id),
FOREIGN KEY (authority_id)
	REFERENCES court_registry (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;