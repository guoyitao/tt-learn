CREATE TABLE `staffs`(
                         `id` INT(10) PRIMARY KEY AUTO_INCREMENT,
                         `name` VARCHAR(24) NOT NULL DEFAULT '' COMMENT '姓名',
                         `age` INT(10) NOT NULL DEFAULT 0 COMMENT '年龄',
                         `pos` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '职位',
                         `add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间'
)COMMENT '员工记录表';

INSERT INTO `staffs`(`name`,`age`,`pos`) VALUES('Ringo', 18, 'manager');
INSERT INTO `staffs`(`name`,`age`,`pos`) VALUES('张三', 20, 'dev');
INSERT INTO `staffs`(`name`,`age`,`pos`) VALUES('李四', 21, 'dev');

/* 创建索引 */
CREATE INDEX idx_staffs_name_age_pos ON `staffs`(`name`,`age`,`pos`);

/* 用到了idx_staffs_name_age_pos索引中的name字段 */
EXPLAIN SELECT * FROM `staffs` WHERE `name` = 'Ringo';

/* 用到了idx_staffs_name_age_pos索引中的name, age字段 */
EXPLAIN SELECT * FROM `staffs` WHERE `name` = 'Ringo' AND `age` = 18;

/* 用到了idx_staffs_name_age_pos索引中的name，age，pos字段 这是属于全值匹配的情况！！！*/
EXPLAIN SELECT * FROM `staffs` WHERE `name` = 'Ringo' AND `age` = 18 AND `pos` = 'manager';

/* 索引没用上，ALL全表扫描 */
EXPLAIN SELECT * FROM `staffs` WHERE `age` = 18 AND `pos` = 'manager';

/* 索引没用上，ALL全表扫描 */
EXPLAIN SELECT * FROM `staffs` WHERE `pos` = 'manager';

/* 用到了idx_staffs_name_age_pos索引中的name字段，pos字段索引失效 */
EXPLAIN SELECT * FROM `staffs` WHERE `name` = 'Ringo' AND `pos` = 'manager';

explain SELECT * FROM `staffs` WHERE `name` = 'Ringo';

explain SELECT * FROM `staffs` WHERE LEFT(`name`, 5) = 'Ringo';

EXPLAIN SELECT * FROM `staffs` WHERE `name` = 'Ringo' AND `age` = 18 AND `pos` = 'manager';

EXPLAIN SELECT * FROM `staffs` WHERE `name` = '张三' AND `age` > 18 AND `pos` = 'dev';