package com.duke.microservice.admin.service;

import com.duke.framework.exception.BusinessException;
import com.duke.framework.utils.ValidationUtils;
import com.duke.microservice.admin.domain.basic.User;
import com.duke.microservice.admin.mapper.basic.UserMapper;
import com.duke.microservice.admin.mapper.extend.UserExtendMapper;
import com.duke.microservice.admin.vm.UserDetailVM;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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
     * @param keyword 关键字
     * @param page    当前页码
     * @param size    每页条数
     * @return List<UserDetailVM>
     */
    @Transactional(readOnly = true)
    public PageInfo<UserDetailVM> select(String keyword, Integer page, Integer size) {
        if (ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(size)) {
            page = 0;
            size = 10;
        }
        PageHelper.startPage(page, size);
        List<User> users = userExtendMapper.select();
        List<UserDetailVM> userDetailVMS = new Page<>();
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(user -> userDetailVMS.add(buildUserDetail(user)));
        }
        BeanUtils.copyProperties(users, userDetailVMS);

        return new PageInfo<>(userDetailVMS);
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
    @Transactional(readOnly = true)
    public User exist(String id) {
        ValidationUtils.notEmpty(id, "userId", "用户id不能为空！");
        User user = userMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException("id为：" + id + "的用户不存在！");
        }
        return user;
    }

    /**
     * 批量校验用户id集合有效性
     *
     * @param ids 用户id集合
     */
    @Transactional(readOnly = true)
    public List<User> exist(List<String> ids) {
        ValidationUtils.notEmpty(ids, "userIds", "用户id集合不能为空！");
        List<User> users = userExtendMapper.selectByIds(ids);
        if (!CollectionUtils.isEmpty(users) && users.size() != ids.size()) {
            throw new BusinessException("存在不合法或者是已经注销的用户！");
        }
        return users;
    }
}
