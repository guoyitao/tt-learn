create DATABASE edu_1;
use edu_1;
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

create DATABASE edu_2;
use edu_2;
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
