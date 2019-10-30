package com.star.account.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * INDEX 控制器
 *
 * @author zx
 * @date 2018/12/23
 */
@Controller
@RequestMapping()
public class IndexController {

    /**
     * 方法请求总入口
     *
     * @return
     */
    @RequestMapping()
    public String index(){
        return "/back/common/main";
    }



}
