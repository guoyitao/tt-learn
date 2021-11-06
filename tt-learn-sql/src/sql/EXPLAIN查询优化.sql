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

# index_merge
explain select * from t2 where t2.content is null or t2.id = 2;
# ref_or_null
explain select * from t2 where t2.content = '' or t2.content is null;

# index_subquery
explain select * from t2 where t2.content in (
    select t3.content from t3
);
create  index idx_content on t3(content);

# **ref**
explain select * from t1,t2 where t1.content=t2.content;

# Using filesort
explain select * from t1 order by content;
create  index idx_content12 on t1(id,content);

# Using temporary
explain select * from t2 group by content;


CREATE TABLE IF NOT EXISTS `article`(
                                        `id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                                        `author_id` INT(10) UNSIGNED NOT NULL COMMENT '作者id',
                                        `category_id` INT(10) UNSIGNED NOT NULL COMMENT '分类id',
                                        `views` INT(10) UNSIGNED NOT NULL COMMENT '被查看的次数',
                                        `comments` INT(10) UNSIGNED NOT NULL COMMENT '回帖的备注',
                                        `title` VARCHAR(255) NOT NULL COMMENT '标题',
                                        `content` VARCHAR(255) NOT NULL COMMENT '正文内容'
) COMMENT '文章';

explain select * from article where id = '3';
INSERT INTO `article`(`author_id`, `category_id`, `views`, `comments`, `title`, `content`) VALUES(1,1,3,3,'3','3');
INSERT INTO `article`(`author_id`, `category_id`, `views`, `comments`, `title`, `content`) VALUES(1,1,4,4,'4','4');

EXPLAIN SELECT id,author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
create index idx_category_id_comments on article(category_id,comments,views);

create index idx_category_id_comments on article(category_id,views);

CREATE TABLE IF NOT EXISTS `class`(
                                      `id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                                      `card` INT(10) UNSIGNED NOT NULL COMMENT '分类'
) COMMENT '商品类别';

CREATE TABLE IF NOT EXISTS `book`(
                                     `bookid` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                                     `card` INT(10) UNSIGNED NOT NULL COMMENT '分类'
) COMMENT '书籍';
create index idx_class_card on class(card);
explain select * from class left join  book on class.card = book.card;

CREATE TABLE IF NOT EXISTS `phone`(
                                      `phone_id` INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                                      `card` INT(10) UNSIGNED NOT NULL COMMENT '分类'
) COMMENT '手机';

explain select * from class
    left join  book on class.card = book.card
    left join phone on book.card = phone.card;
create index idx_book_card on book(card);
create index idx_phone_card on phone(card);

