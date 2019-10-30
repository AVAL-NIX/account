package com.star.account.controller.back;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.star.account.biz.model.R;
import com.star.account.model.entity.AccountDetail;
import com.star.account.model.entity.User;
import com.star.account.model.enums.TypeEnum;
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
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户类
 *
 * @author zx
 * @date 2019/7/31
 */
@RestController
@RequestMapping("/back/user")
public class UserController {


    private static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @Autowired
    private AccountDetailService accountDetailService;

    /**
     * 查询数据
     *
     * @param page
     * @param limit
     * @param user
     * @return
     */
    @GetMapping
    public R listUser(@RequestParam(defaultValue = "1") Integer page, @RequestParam(
            defaultValue = "10") Integer limit, @ModelAttribute User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(user.getName())) {
            queryWrapper.lambda().eq(User::getName, user.getName());
        }
        IPage<User> pages = user.selectPage(new Page<>(page, limit), queryWrapper);
        List<User> list = pages.getRecords();
        pages.setRecords(list.stream().map(item ->
                total(item)
        ).collect(Collectors.toList()));
        return R.page(pages);
    }

    /**
     * 求用户总交易纪录
     * @param user
     * @return
     */
    public User total(User user) {
        List<AccountDetail> list = accountDetailService.listByUserId(user.getId());
        double zhichu = list.stream().filter(v-> v.getType() == TypeEnum.ZHICHU.getCode()).map(v -> v.getPrice()).reduce(0d, (a, b) -> a + b);
        double shouru = list.stream().filter(v-> v.getType() == TypeEnum.SHOURU.getCode()).map(v -> v.getPrice()).reduce(0d, (a, b) -> a + b);
        user.setAmount(shouru - zhichu);
        return user;
    }

}
