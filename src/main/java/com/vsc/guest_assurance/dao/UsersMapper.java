package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Users;
import com.vsc.guest_assurance.vo.backend.BUserDetailVo;
import com.vsc.guest_assurance.vo.backend.BUserListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List<BUserListVo> selectList(@Param("email") String email,
                                 @Param("userName") String userName);

    BUserDetailVo selectByUserId(Integer userId);

    Users selectByEmail(String email);
}