package com.star.account.service;

import com.star.account.biz.model.R;
import com.star.account.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.star.account.model.form.UserForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
public interface UserService extends IService<User> {


    /**
     * 删除
     *
     * @param id
     * @return
     */
    R deleteUserById(Long id);

    /**
     * 修改
     *
     * @param userForm
     * @return
     */
    R updateUser(UserForm userForm);

    /**
     * 添加
     *
     * @param userForm
     * @return
     */
    R addUser(UserForm userForm);

    /**
     * 登录
     *
     * @param loginname
     * @param password
     * @return
     */
    User login(String loginname, String password);

}
