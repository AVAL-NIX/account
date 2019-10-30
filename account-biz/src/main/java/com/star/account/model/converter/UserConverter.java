package com.star.account.model.converter;

import com.star.account.model.entity.User;
import com.star.account.model.form.UserForm;
import org.springframework.beans.BeanUtils;

/**
 * 转换类
 * 
 * @author zx
 * @date 2019/4/24
 */
public class UserConverter
{

    public static User formToObj(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        return user;
    }

    public static User formToObj(UserForm userForm, User user) {
        BeanUtils.copyProperties(userForm, user);
        return user;
    }
}
