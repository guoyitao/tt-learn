package com.guo.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 * author: Guo Yitao
 * create: 2020-08-24 12:03
 **/
@Data
@TableName("t_user")
public class User {
	@TableId
	private Long userId;
	private String username;
	private String ustatus;
}
