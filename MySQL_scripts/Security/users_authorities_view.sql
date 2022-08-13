USE dds_main;

DROP VIEW IF EXISTS users_authorities;

CREATE VIEW users_authorities AS
	SELECT users.username AS username,
    users.enabled AS enabled,
    authorities.authority AS authority
    FROM users, authorities
    WHERE users.username = authorities.username;