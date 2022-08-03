USE dds_main;

DROP TABLE IF EXISTS toDo_list;

CREATE TABLE toDo_list (
id INT PRIMARY KEY AUTO_INCREMENT,
category VARCHAR(30),
dead_line_date DATE,
description VARCHAR(100),
status SET ('Zakończono', 'W trakcie')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;