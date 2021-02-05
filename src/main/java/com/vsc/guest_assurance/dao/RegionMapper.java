package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Region;
import com.vsc.guest_assurance.vo.backend.BRegionPullDownListVo;
import com.vsc.guest_assurance.vo.common.LocationIdsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<BRegionPullDownListVo> getByPid(Integer pid);

    List<BRegionPullDownListVo> getNextLevel(Integer level);

    LocationIdsVo getByRegionName(@Param("provinceName") String provinceName,
                                  @Param("cityName") String cityName,
                                  @Param("districtName") String districtName);
}