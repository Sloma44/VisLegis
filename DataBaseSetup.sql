/* Creating new user */
CREATE USER 'vislegis'@'localhost' IDENTIFIED BY 'L905k[!!!!';

/* Creating new data base and granting the privieges */
CREATE DATABASE IF NOT EXISTS vislegis_DB;
GRANT ALL PRIVILEGES ON vislegis_DB. * TO 'vislegis'@'localhost';
FLUSH PRIVILEGES;

USE vislegis_DB;


/* Tables definitions */

/* Customer table */
CREATE TABLE IF NOT EXISTS customer (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*Customer details table */
CREATE TABLE IF NOT EXISTS customer_contact_info (
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

/* Law cases table */
CREATE TABLE IF NOT EXISTS law_case (
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

/* Correspondance */
CREATE TABLE IF NOT EXISTS correspondence_journal (
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

/* Icomes */
CREATE TABLE IF NOT EXISTS income_for_case (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
income_date DATE,
income_value DECIMAL(8,2),
income_comment VARCHAR(100) DEFAULT '',
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* Customer costs */
CREATE TABLE IF NOT EXISTS customer_case_cost (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
cost_date DATE,
cost_value DECIMAL(8,2),
cost_comment VARCHAR(100) DEFAULT '',
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* Court hearings */
CREATE TABLE IF NOT EXISTS court_hearing_agenda (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
court_hearing_date DATE,
court_hearing_hour TIME,
place VARCHAR(100) DEFAULT '',
room VARCHAR(10) DEFAULT '',
case_id INT,
FOREIGN KEY (case_id)
	REFERENCES law_case (case_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* Courts (institutions) */
CREATE TABLE IF NOT EXISTS court_registry (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(75),
department VARCHAR(75),
street_and_number VARCHAR(75),
postal_and_city VARCHAR(75)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* To-Do List */
CREATE TABLE IF NOT EXISTS todo_list (
id INT PRIMARY KEY AUTO_INCREMENT,
category VARCHAR(30),
dead_line_date DATE,
description VARCHAR(100),
status SET ('Zakończono', 'W trakcie')
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* Values for calculator */
CREATE TABLE IF NOT EXISTS constant_values (
id INT PRIMARY KEY AUTO_INCREMENT,
value_name VARCHAR(100) DEFAULT '',
const_value DECIMAL(8,2)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/* Security */
/* Users */
CREATE TABLE IF NOT EXISTS users (
username VARCHAR(50) PRIMARY KEY NOT NULL,
password CHAR(68) NOT NULL,
enabled TINYINT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/* Roles */
CREATE TABLE IF NOT EXISTS authorities (
username VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL,

UNIQUE KEY authories_idx_1 (username, authority),

CONSTRAINT authorities_ibfk_1
	FOREIGN KEY(username)
	REFERENCES users (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/* Views */
/* Customer-case-court hearing */
CREATE VIEW customer_case_court_hearing AS
	SELECT customer.id AS customer_id, customer.first_name, customer.last_name,
    law_case.case_id, law_case.signature, law_case.case_description,
    court_hearing_agenda.id AS hearing_id, court_hearing_agenda.court_hearing_date,
    court_hearing_agenda.court_hearing_hour, court_hearing_agenda.place, court_hearing_agenda.room
    FROM customer, law_case, court_hearing_agenda
    WHERE law_case.customer_id = customer.id
    AND law_case.case_id = court_hearing_agenda.case_id;
    
/* Customer-case-correspondance */
CREATE VIEW customer_case_journal AS
	SELECT customer.id AS customer_id, customer.first_name, customer.last_name,
    law_case.case_id, law_case.case_description,
    correspondence_journal.id AS journal_id, correspondence_journal.letter_date,
    correspondence_journal.sort, correspondence_journal.internal_number,
    correspondence_journal.recipient_name, correspondence_journal.content,
    correspondence_journal.cost
    FROM customer, law_case, correspondence_journal
    WHERE law_case.customer_id = customer.id
    AND law_case.case_id = correspondence_journal.case_id;
    
/* Customer-case-incomes */
CREATE VIEW customer_case_income AS
	SELECT customer.id AS customer_id, customer.first_name, customer.last_name,
    law_case.case_id, law_case.case_description,
    income_for_case.id AS income_id, income_for_case.income_date,
    income_for_case.income_value, income_for_case.income_comment
    FROM customer, law_case, income_for_case
    WHERE law_case.customer_id = customer.id
    AND law_case.case_id = income_for_case.case_id;

/* Security: user-roles */
CREATE VIEW users_authorities AS
	SELECT users.username AS username,
    users.enabled AS enabled,
    authorities.authority AS authority
    FROM users, authorities
    WHERE users.username = authorities.username;
    
    
/* Table data */
/* User admin data */
INSERT INTO vislegis_DB.users VALUES 
	('admin', '$2a$10$Z1MSoJvutZfGwHKhPK5paOylcEm5KE8GoOCxmIFB3vH9dtla4KxZm', 1);

/* User roles data */    
INSERT INTO vislegis_DB.authorities VALUES
	('admin', 'ROLE_ADMIN');

/* Values for calculator */
INSERT INTO vislegis_DB.constant_values (value_name, const_value) VALUES
	('Stawka - Sprawa karna, Postępowanie przygotowawcze - dochodzenie, z urzędu', 180),
	('Stawka - Sprawa karna, Postępowanie przygotowawcze - śledztwo, z urzędu', 300),
	('Stawka - Sprawa karna, I instancja, Sąd Rejonowy, z urzędu', 420),
	('Stawka - Sprawa karna, II instancja, Sąd Okręgowy, z urzędu', 420),
	('Stawka - Sprawa karna, I instancja, Sąd Okręgowy, z urzędu', 600),
	('Stawka - Sprawa karna, II instancja, Sąd Apelacyjny, z urzędu', 600),

	('Stawka - Sprawa karna, Postępowanie przygotowawcze - dochodzenie, z urzędu', 360),
	('Stawka - Sprawa karna, Postępowanie przygotowawcze - śledztwo, z urzędu', 600),
	('Stawka - Sprawa karna, I instancja, Sąd Rejonowy, z wyboru', 840),
	('Stawka - Sprawa karna, II instancja, Sąd Okręgowy, z wyboru', 840),
	('Stawka - Sprawa karna, I instancja, Sąd Okręgowy, z wyboru', 1200),
	('Stawka - Sprawa karna, II instancja, Sąd Apelacyjny, z wyboru', 1200),

	('Procent od stawki podstawowej- Sprawa karna [%]', 20),
	('Premia- Sprawa karna [%]', 50),

	('Stawka podatku VAT [%]', 23);