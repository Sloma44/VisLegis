USE dds_main;

DROP TABLE IF EXISTS customer_case_cost;

CREATE TABLE customer_case_cost (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
cost_date DATE,
cost_value DECIMAL(8,2),
cost_comment VARCHAR(100) DEFAULT '',
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;