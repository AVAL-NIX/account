package com.star.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.account.biz.model.R;
import com.star.account.mapper.AccountDetailMapper;
import com.star.account.mapper.TsAccountMapper;
import com.star.account.model.converter.AccountDetailFormConverter;
import com.star.account.model.converter.TsAccountConverter;
import com.star.account.model.entity.AccountDetail;
import com.star.account.model.entity.TsAccount;
import com.star.account.model.entity.User;
import com.star.account.model.form.AccountDetailForm;
import com.star.account.model.form.TsAccountForm;
import com.star.account.service.AccountDetailService;
import com.star.account.service.TsAccountService;
import com.star.account.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@Service
public class TsAccountServiceImpl extends ServiceImpl<TsAccountMapper, TsAccount> implements TsAccountService {

    @Autowired
    TsAccountMapper tsAccountMapper;


    @Override
    public R save(TsAccountForm tsAccountForm) {
        TsAccount account = TsAccountConverter.formToObj(tsAccountForm);
        tsAccountMapper.insert(account);
        return R.ok();
    }

}
