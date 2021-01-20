package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.vo.backend.BStoreListVo;
import com.vsc.guest_assurance.vo.backend.BStoresThumbsUpVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stores record);

    int insertSelective(Stores record);

    Stores selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stores record);

    int updateByPrimaryKey(Stores record);

    List<Stores> selectByAccoutNumber(String accountnumber);

    List<BStoreListVo> selectList(String keyWord);

    List<Stores> selectListAll(String keyWord);

    List<BStoresThumbsUpVo> getThumbsUpStores(@Param("latitude") Float latitude,
                                              @Param("longitude") Float longitude);
}