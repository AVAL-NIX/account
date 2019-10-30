package com.star.account.service.impl;

import com.star.account.biz.model.R;
import com.star.account.model.converter.UserConverter;
import com.star.account.model.entity.User;
import com.star.account.mapper.UserMapper;
import com.star.account.model.form.UserForm;
import com.star.account.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AUTH_OTHER
 * @since 2019-10-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R deleteUserById(Long id) {
        int row = userMapper.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        }
        return R.error("删除失败");
    }

    @Override
    public R updateUser(UserForm userForm) {
        User user = userMapper.selectById(userForm.getId());
        user = UserConverter.formToObj(userForm);
        int row = userMapper.updateById(user);
        if (row > 0) {
            return R.ok("修改成功");
        }
        return R.error("修改失败");
    }

    @Override
    public R addUser(UserForm userForm) {
        User user = UserConverter.formToObj(userForm);
        int row = userMapper.insert(user);
        if (row > 0) {
            return R.ok("添加成功");
        }
        return R.error("添加失败");
    }

    @Override
    public User login(String loginname, String password) {
        return null;
    }
}
