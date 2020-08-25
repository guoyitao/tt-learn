CREATE DATABASE user_db;

USE `user_db`;
CREATE TABLE `t_user` (
  `user_id` BIGINT(20) PRIMARY KEY,
  `username` VARCHAR(100)  NOT NULL,
  `ustatus` VARCHAR(50) NOT NULL,
)


CREATE TABLE t_udict(
	dictid BIGINT(20) PRIMARY KEY,
	ustatus VARCHAR(100) NOT NULL,
	uvalues VARCHAR(100) NOT NULL
);


USE user_db;
CREATE TABLE t_udict(
	dictid BIGINT(20) PRIMARY KEY,
	ustatus VARCHAR(100) NOT NULL,
	uvalues VARCHAR(100) NOT NULL
);


USE edu_1;
CREATE TABLE t_udict(
	dictid BIGINT(20) PRIMARY KEY,
	ustatus VARCHAR(100) NOT NULL,
	uvalues VARCHAR(100) NOT NULL
);

USE edu_2;
CREATE TABLE t_udict(
	dictid BIGINT(20) PRIMARY KEY,
	ustatus VARCHAR(100) NOT NULL,
	uvalues VARCHAR(100) NOT NULL
);
