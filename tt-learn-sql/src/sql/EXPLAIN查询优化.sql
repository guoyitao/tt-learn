explain select *from t1;

# ----------id----------
# id相同自上而下
explain select * from t1,t2,t3 where t1.id=t2.id and t3.id;

# id 不同，id 不同，如果是子查询，id 的序号会递增，id 值越大优先级越高，越先被执行
explain select t2.*
    from t2
    where id = (select id from t1
    where id = (select t3.id from t3 where t3.content = '')
        );

# 有相同也有不同
# id 如果相同，可以认为是一组，从上往下顺序执行；在所有组中，id 值越大，优先级越高，越先执行衍生 = DERIVED
explain SELECT t2.* FROM
( SELECT t3.id from t3 wHERE t3.content ='') s1,t2
WHERE s1.id=t2. id;

#-------------type-----------------
# const
explain select * from (select * from t1 where id = 1) d1;

# ref
explain select * from t1,t2 where t1.content = t2.content;
create index idx_content on t2(content);
explain select * from t1,t2 where t1.content = t2.content;

# eq_ref：唯一性索引扫描，对于每个索引键，表中只有一条记录与之匹配。常见于主键或唯一索引扫描。
explain select * from t1,t2 where t1.id = t2.id;

# range
explain select * from t4 where id in (1,2,3);

# index
explain select id from t2;
explain select * from t2 order by content;

#all
explain select * from t1 ,t3 where t1.content = t3.content;