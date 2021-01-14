package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.User;
import com.vsc.guest_assurance.vo.BackendUserListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 搜索
     * @param email
     * @param userName
     * @param privilege
     * @return
     */
    List<BackendUserListVo> selectList(@Param("email") String email,
                                       @Param("userName") String userName,
                                       @Param("privilege") Integer privilege);
}