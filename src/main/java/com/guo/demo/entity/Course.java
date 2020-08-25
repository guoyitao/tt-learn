package com.guo.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * description:
 * author: Guo Yitao
 * create: 2020-08-23 18:03
 **/
@Data
public class Course {
	@TableId(value = "cid")
	private Long cid;
	private String cname;
	private Long userId;
	private String cstatus;
}
