package com.star.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.star.account.biz.model.R;
import com.star.account.model.converter.AccountDetailFormConverter;
import com.star.account.model.entity.AccountDetail;
import com.star.account.mapper.AccountDetailMapper;
import com.star.account.model.entity.User;
import com.star.account.model.enums.TypeEnum;
import com.star.account.model.form.AccountDetailForm;
import com.star.account.service.AccountDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AccountDetailServiceImpl extends ServiceImpl<AccountDetailMapper, AccountDetail> implements AccountDetailService {

    @Autowired
    AccountDetailMapper accountDetailMapper;

    @Autowired
    UserService userService;

    @Override
    public R save(AccountDetailForm accountDetailForm) {
        AccountDetail accountDetail = AccountDetailFormConverter.formToObj(accountDetailForm);
        if (accountDetailForm.getUserId() > 0) {
            User user = userService.getById(accountDetail.getUserId());
            if (user == null) {

                return R.error("添加失败,用户为空");
            } else {
                accountDetail.setUserName(user.getName());
            }
        } else {
            //如果没有用户. 金额均分到3个人身上
            List<User> userList = userService.list();
            for (User user1 : userList) {
                AccountDetail userAccountDetail = new AccountDetail();
                BeanUtils.copyProperties(accountDetail, userAccountDetail);
                //支出为负 , 收入为正
                userAccountDetail.setPrice(accountDetail.getPrice() / userList.size());
                userAccountDetail.setUserName(user1.getName());
                userAccountDetail.setUserId(user1.getId());
                int row = accountDetailMapper.insert(userAccountDetail);
                if (row <= 0) {

                    return R.error("添加用户支出信息失败");
                }
            }
        }
        int row = accountDetailMapper.insert(accountDetail);
        if (row <= 0) {

            return R.error("添加失败");
        }
        return R.ok("添加成功");
    }


    @Override
    public List<AccountDetail> listByUserId(Long id) {
        QueryWrapper<AccountDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AccountDetail::getUserId, id);
        return list(queryWrapper);
    }
}
