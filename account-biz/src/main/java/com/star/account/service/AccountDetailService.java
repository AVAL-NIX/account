package com.star.account.service;

import com.star.account.biz.model.R;
import com.star.account.model.entity.AccountDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.star.account.model.form.AccountDetailForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
public interface AccountDetailService extends IService<AccountDetail> {

    R save(AccountDetailForm roleForm);

    List<AccountDetail> listByUserId(Long id);
}
