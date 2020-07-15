package com.star.account.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.account.biz.model.R;
import com.star.account.common.utils.ValidatorUtils;
import com.star.account.model.converter.TsAccountConverter;
import com.star.account.model.entity.AccountDetail;
import com.star.account.model.entity.TsAccount;
import com.star.account.model.enums.TypeEnum;
import com.star.account.model.form.TsAccountForm;
import com.star.account.model.form.TsAccountForm;
import com.star.account.model.form.TsAccountForm.TsAccountGroup;
import com.star.account.service.AccountDetailService;
import com.star.account.service.TsAccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@RestController
@RequestMapping("/back/ts")
public class TsAccountController {

    private static final Logger log = LogManager.getLogger(TsAccountController.class);

    @Autowired
    TsAccountService tsAccountService;

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(
            defaultValue = "10") Integer limit, @ModelAttribute TsAccount tsAccount) {
        QueryWrapper<TsAccount> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(tsAccount.getUsername())){
            queryWrapper.lambda().eq(TsAccount::getUsername,tsAccount.getUsername());
        }
        IPage<TsAccount> pages = tsAccount.selectPage(new Page<>(page, limit), queryWrapper);
        return R.page(pages);
    }


    /**
     * 修改数据
     *
     * @return
     */
    @PostMapping
    public R update(@ModelAttribute @Valid TsAccountForm tsAccountForm) {
        ValidatorUtils.validateEntity(tsAccountForm, TsAccountGroup.class);
        TsAccount tsAccount = TsAccountConverter.formToObj(tsAccountForm);
        TsAccount db = tsAccountService.getById(tsAccount.getId());
        tsAccount.setCount(db.getCount());
        Boolean b = tsAccount.updateById();
        if(!b){
            return R.error();
        }
        return R.ok();
    }



}

