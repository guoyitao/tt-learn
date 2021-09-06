

使用 EXPLAIN 关键字可以模拟优化器执行 SQL 查询语句，从而知道 MySQL 是如何处理你的 SQL 语句的。分 析你的查询语句或是表结构的性能瓶颈。 

> 用法： Explain+SQL 语句
>
> ![image-20210906191502007](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906191502007.png)

#### select_type 数据读取的操作类型

| SIMPLE               |                                                              |
| -------------------- | ------------------------------------------------------------ |
| SIMPLE               | 简单的 select 查询,查询中不包含子查询或者 UNION              |
| PRIMARY              | 查询中若包含任何复杂的子部分，最外层查询则被标记为 Primary   |
| DERIVED（临时表）    | 在 FROM 列表中包含的子查询被标记为 DERIVED(衍生) MySQL 会递归执行这些子查询, 把结果放在临时表里。在SELECT或WHERE列表中包含了子查询 （增加系统负担） |
| SUBQUERY             | 在SELECT或WHERE列表中包含了子查询                            |
| DEPEDENT SUBQUERY    | 在SELECT或WHERE列表中包含了子查询,子查询基于外层             |
| UNCACHEABLE SUBQUERY | 无法使用缓存的子查询                                         |
| UNION                | 若第二个SELECT出现在UNION之后，则被标记为UNION； 若UNION包含在FROM子句的子查询中,外层SELECT将被标记为：DERIVED |
| UNION RESULT         | 从UNION表获取结果的SELECT                                    |

#### id ：表的执行顺序

优化建议：小表驱动大表

```sql
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
```

#### table 关于那张表的

### type 

查询的访问类型。是较为重要的一个指标，结果值从最好到最坏依次是：

- 全部：system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > All

- **常见**：system > const > eq_ref > ref >range > index > All

一般至少达到range，最好ref

1. **system**：表只有一行记录（等于系统表），这是 const 类型的特列，平时不会出现，这个也可以忽略不计

2. **const** ：表示通过索引一次就找到了,const 用于比较 primary key 或者 unique 索引。因为只匹配一行数据，所以很快如将主键置于 where 列表中，MySQL 就能将该查询转换为一个常量。

   ```sql
   explain select * from (select * from t1 where id = 1) d1;
   ```

   ![image-20210906214115146](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906214115146-1630935678033.png)

3. **eq_ref**：唯一性索引扫描，对于每个索引键，表中只有一条记录与之匹配。常见于主键或唯一索引扫描。

   ```sql
   explain select * from t1,t2 where t1.id = t2.id;
   ```

   ![image-20210906214955202](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906214955202-1630936196340.png)

   t2.id在t2是唯一索引

4. **ref**：非唯一性索引扫描，返回匹配某个单独值的所有行.本质上也是一种索引访问，它返回所有匹配某个单独值的行， 然而，它可能会找到多个符合条件的行，所以他应该属于查找和扫描的混合体。

   ```sql
   explain select * from t1,t2 where t1.content = t2.content
   ```

   ![image-20210906204633167](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906204633167-1630932395112.png)

   添加索引

   `create index idx_content on t2(content);`

   之后

   ![image-20210906204856421](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906204856421-1630932538372.png)

5. **range**：只检索给定范围的行,使用一个索引来选择行。key 列显示使用了哪个索引一般就是在你的 where 语句中出现 了 between、<、>、in 等的查询这种范围扫描索引扫描比全表扫描要好，因为它只需要开始于索引的某一点，而结束语另一点，不用扫描全部索引。 

   ```sql
   explain select * from t4 where id in (1,2,3);
   ```

   ![image-20210906220002475](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906220002475-1630936803531.png)

6. **index** :出现index是sql使用了索引但是没用通过索引进行过滤，一般是使用了覆盖索引或者是利用索引进行了排序分组

    ```sql
    explain select * from t2 order by content;
    ```

    ![image-20210906220302695](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906220302695-1630936984007.png)

    ```sql
    explain select id from t2;
    ```

    ![image-20210906220333644](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906220333644-1630937014677.png)
    
7. **all** Full Table Scan，将遍历全表以找到匹配的行.

    ```sql
    explain select * from t1 ,t3 where t1.content =t3.content
    ```

    ![image-20210906220825582](C:\mycode\code\tt-learn-master\tt-learn-sql\src\md\EXPLAIN查询优化.assets\image-20210906220825582-1630937306634.png)

