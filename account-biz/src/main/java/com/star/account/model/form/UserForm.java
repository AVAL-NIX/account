package com.star.account.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User 提交对象
 * {@link com.star.account.model.entity.User}
 *
 * @author zx
 * @date 2018/12/18
 */
@Data
public class UserForm {

    /** 登录ID **/
    private Long id;

    /** 用户名 **/
    @NotEmpty(message = "用户名必填")
    private String username;

    /** 密码 **/
    @NotEmpty(message = "密码必填")
    private String password;

}
