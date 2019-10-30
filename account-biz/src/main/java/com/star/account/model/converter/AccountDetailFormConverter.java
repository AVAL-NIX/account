package com.star.account.model.converter;

import com.star.account.model.entity.AccountDetail;
import com.star.account.model.form.AccountDetailForm;
import org.springframework.beans.BeanUtils;

/**
 * @description:
 * <br>
 * @date: 2019/10/29 11:33
 * @author: zhengxin
 * @version: mall V1.0
 * @since: JDK 1.8
 */
public class AccountDetailFormConverter {

    public static AccountDetail formToObj(AccountDetailForm accountDetailForm) {
        AccountDetail accountDetail = new AccountDetail();
        BeanUtils.copyProperties(accountDetailForm, accountDetail);
        return accountDetail;
    }

    public static AccountDetail formToObj(AccountDetailForm accountDetailForm, AccountDetail accountDetail) {
        BeanUtils.copyProperties(accountDetailForm, accountDetail);
        return accountDetail;
    }
}
