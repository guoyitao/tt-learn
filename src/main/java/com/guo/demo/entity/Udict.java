package com.guo.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_udict")
public class Udict {
    @TableId
    private Long dictid;
    private String ustatus;
    private String uvalues;
}
