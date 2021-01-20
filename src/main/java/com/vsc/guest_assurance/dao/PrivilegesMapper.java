package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Privileges;
import com.vsc.guest_assurance.vo.backend.BPrivilegeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivilegesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privileges record);

    int insertSelective(Privileges record);

    Privileges selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privileges record);

    int updateByPrimaryKey(Privileges record);

    List<BPrivilegeVo> selectPrivileges(@Param("privilegeIds") String privilegeIds,
                                        @Param("type") String type,
                                        @Param("ids") List<Integer> ids);
}