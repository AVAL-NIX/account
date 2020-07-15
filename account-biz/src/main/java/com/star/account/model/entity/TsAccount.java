package com.star.account.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@Data
public class TsAccount extends Model<TsAccount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 帐号
     */
    private String username;

    /**
     * 备注
     */
    private String password;


    /**
     * 备注
     */
    private String token;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
