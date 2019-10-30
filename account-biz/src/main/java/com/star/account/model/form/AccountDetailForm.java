package com.star.account.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@Data
public class AccountDetailForm {

    /**
     * 交易价格
     */
    @NotNull(message = " 交易价格必填", groups = AccountDetailFormGroup.class)
    private Double price;

    /**
     * 交易类型,1支出,2收入
     */
    @NotNull(message = " 交易类型必填", groups = AccountDetailFormGroup.class)
    private Integer type;

    /**
     * 关联用户
     */
    private Long userId;

    /**
     * 备注
     */
    private String descrition;


    public interface AccountDetailFormGroup{}
}
