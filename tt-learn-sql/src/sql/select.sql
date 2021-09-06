-- 查询两表公有的条件
SELECT * FROM t_emp e INNER JOIN t_dept d ON e.`deptId`=d.`id`;

-- 查询左表连接到右的条件，右没有就补null
SELECT * FROM t_emp e LEFT JOIN t_dept d ON e.`deptId`=d.`id`;

-- 查询左表连接到右的条件，左表独有的
SELECT * FROM t_emp e LEFT JOIN t_dept d ON e.`deptId`=d.`id` where d.id IS NULL;


-- 查询右表连接到左的条件，左没有就补null
SELECT * FROM t_emp e RIGHT JOIN t_dept d ON e.`deptId`=d.`id`;

-- 查询左表连接到右的条件，右表独有的
SELECT * FROM t_emp e RIGHT JOIN t_dept d ON e.`deptId`=d.`id` where e.`deptId` IS NULL;

# 共有的
SELECT * FROM t_emp e INNER JOIN t_dept d ON e.`deptId`=d.`id`;

# 全有
# (mysql 不支持) SELECT * FROM t_emp e FULL OUTER JOIN t_dept d ON e.`deptId`=d.`id`;
# a+b
SELECT * FROM t_emp e LEFT JOIN t_dept d ON e.`deptId`=d.`id`
UNION #去重 合并
SELECT * FROM t_emp e RIGHT JOIN t_dept d ON e.`deptId`=d.`id`;

# a和b各自的独有
SELECT * FROM t_emp e LEFT JOIN t_dept d ON e.`deptId`=d.`id` where d.id IS NULL
UNION #去重 合并
SELECT * FROM t_emp e RIGHT JOIN t_dept d ON e.`deptId`=d.`id` where e.`deptId` IS NULL;
