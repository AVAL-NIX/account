package com.star.account.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@Data
public class AccountDetail extends Model<AccountDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 交易价格
     */
    private Double price;

    /**
     * 交易类型,1支出,2收入
     */
    private Integer type;

    /**
     * 交易类型,1支出,2收入
     */
    @TableField(exist=false)
    private String typeStr;

    /**
     * 关联用户
     */
    private Long userId;

    /**
     * 关联用户
     */
    private String userName;

    /**
     * 备注
     */
    private String descrition;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
