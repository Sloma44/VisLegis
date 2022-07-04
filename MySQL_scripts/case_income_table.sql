USE dds_main;

DROP TABLE IF EXISTS income_for_case;

CREATE TABLE income_for_case (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
income_date DATE,
income_value DECIMAL(8,2),
income_comment VARCHAR(100) DEFAULT '',
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;