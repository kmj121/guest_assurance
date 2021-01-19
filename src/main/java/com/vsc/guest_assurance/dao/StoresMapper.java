package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.vo.BackendStoreListVo;

import java.util.List;

public interface StoresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stores record);

    int insertSelective(Stores record);

    Stores selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stores record);

    int updateByPrimaryKey(Stores record);

    List<Stores> selectByAccoutNumber(String accountnumber);

    List<BackendStoreListVo> selectList(String keyWord);

    List<Stores> selectListAll(String keyWord);
}