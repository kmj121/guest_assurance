package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.Constant;
import com.vsc.guest_assurance.dao.RegionMapper;
import com.vsc.guest_assurance.vo.backend.BRegionPullDownListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class RegionService {

    @Autowired
    private RegionMapper regionMapper;

    public List<BRegionPullDownListVo> pullDownList(Integer province, Integer city) {
        List<BRegionPullDownListVo> listVos = new ArrayList<>();
        if(city != null) {
            listVos = regionMapper.getByPid(city).stream().filter(u -> u.getLevel() == 3).collect(Collectors.toList());
        } else if(province != null) {
            listVos = regionMapper.getByPid(province).stream().filter(u -> u.getLevel() == 2).collect(Collectors.toList());
        } else {
            listVos = regionMapper.getNextLevel(Constant.REGION_ID_CHINA);
        }
        return listVos;
    }
}
