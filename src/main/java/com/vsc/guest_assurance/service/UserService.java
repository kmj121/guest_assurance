package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.dao.UserMapper;
import com.vsc.guest_assurance.vo.BackendUserListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public PageBean<BackendUserListVo> list(String email, String userName, Integer privilege, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<BackendUserListVo> vos = userMapper.selectList(email, userName, privilege);
        PageInfo<BackendUserListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }
}
