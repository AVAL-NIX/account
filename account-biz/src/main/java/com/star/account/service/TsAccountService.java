package com.star.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.star.account.biz.model.R;
import com.star.account.model.entity.AccountDetail;
import com.star.account.model.entity.TsAccount;
import com.star.account.model.form.AccountDetailForm;
import com.star.account.model.form.TsAccountForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
public interface TsAccountService extends IService<TsAccount> {

    R save(TsAccountForm tsAccountForm);

}
