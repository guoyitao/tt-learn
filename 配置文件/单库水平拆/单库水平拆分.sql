create DATABASE shardingjdbclearn_course_db;

CREATE TABLE course_1(
	cid BIGINT(20) PRIMARY KEY,
	cname VARCHAR(50) not null,
	user_id BIGINT(20) not null,
	cstatus VARCHAR(10) not null

);

CREATE TABLE course_2(
	cid BIGINT(20) PRIMARY KEY,
	cname VARCHAR(50) not null,
	user_id BIGINT(20) not null,
	cstatus VARCHAR(10) not null

);
