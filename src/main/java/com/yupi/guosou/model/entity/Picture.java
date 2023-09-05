package com.yupi.guosou.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片
 */
@Data
public class Picture implements Serializable {
    private String title;

    private String url;

    private static final long serialVersionUID = 1L;
}
