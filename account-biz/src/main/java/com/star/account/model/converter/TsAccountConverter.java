package com.star.account.model.converter;

import com.star.account.model.entity.TsAccount;
import com.star.account.model.entity.User;
import com.star.account.model.form.TsAccountForm;
import com.star.account.model.form.UserForm;
import org.springframework.beans.BeanUtils;

/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class TsAccountConverter
{

    public static TsAccount formToObj(TsAccountForm tsAccountForm) {
        TsAccount tsAccount = new TsAccount();
        BeanUtils.copyProperties(tsAccountForm, tsAccount);
        return tsAccount;
    }

    public static TsAccount formToObj(TsAccountForm tsAccountForm, TsAccount tsAccount) {
        BeanUtils.copyProperties(tsAccountForm, tsAccount);
        return tsAccount;
    }
}
