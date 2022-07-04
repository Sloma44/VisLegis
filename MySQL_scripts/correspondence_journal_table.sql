USE dds_main;

DROP TABLE IF EXISTS correspondence_journal;

CREATE TABLE correspondence_journal (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
letter_date DATE,
sort SET ('wychodząca', 'przychodząca'),
internal_number VARCHAR(20),
recipient_name VARCHAR(100) DEFAULT '',
content VARCHAR(100) DEFAULT '',
cost DECIMAL(8,2),
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;