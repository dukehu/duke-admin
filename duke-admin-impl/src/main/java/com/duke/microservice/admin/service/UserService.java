package com.duke.microservice.admin.service;

import com.duke.framework.exception.BusinessException;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.domain.basic.User;
import com.duke.microservice.admin.mapper.basic.UserMapper;
import com.duke.microservice.admin.mapper.extend.UserExtendMapper;
import com.duke.microservice.admin.vm.UserDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created duke on 2018/8/4
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtendMapper userExtendMapper;

    /**
     * 用户列表查询
     *
     * @return List<UserDetailVM>
     */
    @Transactional(readOnly = true)
    public List<UserDetailVM> select() {
        List<UserDetailVM> userDetailVMS = new ArrayList<>();
        List<User> users = userExtendMapper.select();
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(user -> userDetailVMS.add(buildUserDetail(user)));
        }
        return userDetailVMS;
    }

    /**
     * 根据用户id查找
     *
     * @param id 用户id
     * @return UserDetailVM
     */
    @Transactional(readOnly = true)
    public UserDetailVM selectById(String id) {
        User user = this.exist(id);
        return buildUserDetail(user);
    }

    /**
     * 构造用户信息
     *
     * @param user 用户对象
     * @return UserDetailVM
     */
    private UserDetailVM buildUserDetail(User user) {
        return new UserDetailVM(
                user.getId(), user.getGender(), user.getRealName(), user.getNickName(), user.getLoginName(),
                user.getLoginMobile(), user.getLoginEmail(), user.getStatus(), user.getAvatar()
        );
    }

    /**
     * 校验用户id有效性
     *
     * @param id 用户id
     * @return User
     */
    private User exist(String id) {
        ValidationUtils.notEmpty(id, "userId", "用户id不能为空！");
        User user = userMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException("id为：" + id + "的用户不存在！");
        }
        return user;
    }
}
