USE dds_main;

CREATE TABLE authorities (
username VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL,

UNIQUE KEY authories_idx_1 (username, authority),

CONSTRAINT authorities_ibfk_1
	FOREIGN KEY(username)
	REFERENCES users (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;