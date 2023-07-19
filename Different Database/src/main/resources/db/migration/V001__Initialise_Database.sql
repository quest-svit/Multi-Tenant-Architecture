CREATE TABLE if not exists public.DATASOURCECONFIG (
	id int PRIMARY KEY,
	driverclassname VARCHAR(255),
	url VARCHAR(255),
	name VARCHAR(255),
	username VARCHAR(255),
	password VARCHAR(255),
	initialize BOOLEAN
);

INSERT INTO DATASOURCECONFIG VALUES (1, 'org.h2.Driver', 'jdbc:h2:file:./DB/tenant1Database', 'TEST1', 'sa', 'sa', true);
INSERT INTO DATASOURCECONFIG VALUES (2, 'org.h2.Driver', 'jdbc:h2:file:./DB/tenant2Database', 'TEST2', 'sa', 'sa', true);
