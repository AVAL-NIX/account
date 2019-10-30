package com.star.account.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.account.biz.model.R;
import com.star.account.common.utils.ValidatorUtils;
import com.star.account.model.entity.AccountDetail;
import com.star.account.model.entity.User;
import com.star.account.model.enums.TypeEnum;
import com.star.account.model.form.AccountDetailForm;
import com.star.account.model.form.AccountDetailForm.AccountDetailFormGroup;
import com.star.account.model.form.UserForm;
import com.star.account.service.AccountDetailService;
import com.star.account.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping("/back/accountDetail")
public class AccountDetailController {

    private static final Logger log = LogManager.getLogger(AccountDetailController.class);

    @Autowired
    AccountDetailService accountDetailService;

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(
            defaultValue = "10") Integer limit, @ModelAttribute AccountDetail accountDetail) {
        QueryWrapper<AccountDetail> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(accountDetail.getUserName())) {
            queryWrapper.lambda().eq(AccountDetail::getUserName, accountDetail.getUserName());
        }
        IPage<AccountDetail> pages = accountDetail.selectPage(new Page<>(page, limit), queryWrapper);
        pages.getRecords().stream().map(v -> {
            v.setTypeStr(TypeEnum.getEnum(v.getType()).getValue());
            return v;
        }).collect(Collectors.toList());
        return R.page(pages);
    }


    /**
     * 添加数据
     *
     * @return
     */
    @PostMapping
    public R save(@ModelAttribute @Valid AccountDetailForm accountDetailForm) {
        ValidatorUtils.validateEntity(accountDetailForm, AccountDetailFormGroup.class);
        R result = accountDetailService.save(accountDetailForm);
        return R.ok(result.getMsg());
    }


}

