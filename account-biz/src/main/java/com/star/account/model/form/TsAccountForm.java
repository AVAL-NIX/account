package com.star.account.model.form;

import com.star.account.model.form.AccountDetailForm.AccountDetailFormGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@Data
public class TsAccountForm {

    private Long id;

    @NotNull(message = " 帐号必填", groups = TsAccountGroup.class)
    private String username;


    @NotNull(message = " 密码必填", groups = TsAccountGroup.class)
    private String password;

    @NotNull(message = "token必填", groups = TsAccountGroup.class)
    private String token;

    public interface TsAccountGroup{}
}
