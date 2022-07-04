USE dds_main;

DROP TABLE IF EXISTS court_hearing_agenda;

CREATE TABLE court_hearing_agenda (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
court_hearing_date DATE,
court_hearing_hour TIME,
place VARCHAR(100) DEFAULT '',
room VARCHAR(10) DEFAULT '',
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;