package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Privileges;
import com.vsc.guest_assurance.vo.BPrivilegeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivilegesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privileges record);

    int insertSelective(Privileges record);

    Privileges selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privileges record);

    int updateByPrimaryKey(Privileges record);

    List<Privileges> selectUnAchievedByIds(@Param("ids") List<Integer> ids);

    List<BPrivilegeVo> selectPrivileges(@Param("privilegeIds") String privilegeIds,
                                        @Param("type") String type,
                                        @Param("ids") List<Integer> ids,
                                        @Param("language") Integer language);

    int selectByIds(@Param("customerFlag") boolean customerFlag,
                    @Param("ids") List<Integer> ids);

    List<BPrivilegeVo> selectByCustomerFlag(@Param("customerFlag") Integer customerFlag,
                                            @Param("language") Integer language);
}