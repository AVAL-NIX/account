package com.star.account.controller.back;

import com.star.account.controller.base.BaseController;
import com.star.account.model.enums.TypeEnum;
import com.star.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * <br>
 * @date: 2019/9/4 14:45
 * @author: zhengxin
 * @version: mall V1.0
 * @since: JDK 1.8
 */
@Controller
@RequestMapping("/back/home")
public class BackHomeController extends BaseController {

    /**
     * 文章主页
     *
     * @return
     */
    @GetMapping("/user")
    public String user(Model model) {
        return "/back/user/user-list";
    }
    /**
     * 文章主页
     *
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(Model model) {
        return "/back/user/welcome";
    }

    /**
     * 账单主页
     *
     * @return
     */
    @GetMapping("/accountDetail")
    public String accountDetail(Model model) {

        return "/back/account/account-detail-list";
    }

    /**
     * TS 帐号
     *
     * @return
     */
    @GetMapping("/ts")
    public String ts(Model model) {
        return "/back/ts/ts-list";
    }



    /**
     * 账单主页-add
     *
     * @return
     */
    @GetMapping("/accountDetail/add")
    public String accountDetailAdd(Model model) {
        model.addAttribute("typeEnum", TypeEnum.values());
        model.addAttribute("users", userService.list());
        return "/back/account/account-detail-add";
    }


    @Autowired
    UserService userService;


}
