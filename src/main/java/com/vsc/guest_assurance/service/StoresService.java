package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.*;
import com.vsc.guest_assurance.dao.StoresMapper;
import com.vsc.guest_assurance.dao.ThumbsUpHistoryMapper;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.entity.ThumbsUpHistory;
import com.vsc.guest_assurance.util.LocationUtil;
import com.vsc.guest_assurance.vo.LocationVo;
import com.vsc.guest_assurance.vo.backend.BRegionPullDownListVo;
import com.vsc.guest_assurance.vo.backend.BStoreListVo;
import com.vsc.guest_assurance.vo.backend.BStoresThumbsUpVo;
import com.vsc.guest_assurance.vo.common.LocationIdsVo;
import com.vsc.guest_assurance.vo.common.ParseStoresVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class StoresService {
    @Autowired
    private StoresMapper storesMapper;
    @Autowired
    private ThumbsUpHistoryMapper thumbsUpHistoryMapper;
    @Autowired
    private RegionService regionService;

    public void updateStores() throws Exception {
        List<ParseStoresVo> parseList = ParseStores.storeParser();
        for (ParseStoresVo item : parseList) {
            if(StringUtils.isBlank(item.getAccountid())) {
                continue;
            }
            List<Stores> storesList = storesMapper.selectByAccoutId(item.getAccountid());
            if(storesList == null || storesList.size() == 0) {
                LocationVo vo = LocationUtil.getLocationMsg(item.getAddress1_longitude(), item.getAddress1_latitude());
                List<BRegionPullDownListVo> result = regionService.getByCid(vo.getAdcode());
                Stores stores = new Stores();
                BeanUtils.copyProperties(stores, item);
                //添加省市区信息
                List<BRegionPullDownListVo> level1Vos = result == null ? null : result.stream().filter(u -> u.getLevel() == 1).collect(Collectors.toList());
                List<BRegionPullDownListVo> level2Vos = result == null ? null : result.stream().filter(u -> u.getLevel() == 2).collect(Collectors.toList());
                List<BRegionPullDownListVo> level3Vos = result == null ? null : result.stream().filter(u -> u.getLevel() == 3).collect(Collectors.toList());
                Integer provinceId = level1Vos == null || level1Vos.size() > 1 ? null : level1Vos.get(0).getRegionId();
                Integer cityId = level2Vos == null || level2Vos.size() > 1 ? null : level2Vos.get(0).getRegionId();
                Integer districtId = level3Vos == null || level3Vos.size() > 1 ? null : level3Vos.get(0).getRegionId();
                stores.setProvince_id(provinceId);
                stores.setCity_id(cityId);
                stores.setDistrict_id(districtId);
                stores.setCreate_time(new Date());
                stores.setThumbs_up_num(Constant.FALSE);
                stores.setThumbs_up_points(Constant.FALSE);
                storesMapper.insert(stores);
            } else if (storesList != null && storesList.size() == 1){
                BeanUtils.copyProperties(storesList.get(0), item);
                storesList.get(0).setUpdate_time(new Date());
                storesMapper.updateByPrimaryKey(storesList.get(0));
            } else if(storesList != null && storesList.size() > 1) {
                throw new ApiException(MessageCode.CODE_ONE_MORE_DATA, "门店");
            }
        }
    }

    public PageBean<BStoreListVo> list(String keyWord, Integer page, Integer size, String orderName, Integer orderType) {
        String orderBy = "";
        if("id".equals(orderName)) {
            orderBy = orderType == 0 ? "id asc" : "id desc";
        }
        if("accountnumber".equals(orderName)) {
            orderBy = orderType == 0 ? "accountnumber asc" : "accountnumber desc";
        }
        if("name".equals(orderName)) {
            orderBy = orderType == 0 ? "name asc" : "name desc";
        }
        if("_ownerid_value".equals(orderName)) {
            orderBy = orderType == 0 ? "_ownerid_value asc" : "_ownerid_value desc";
        }
        if("address1_composite".equals(orderName)) {
            orderBy = orderType == 0 ? "address1_composite asc" : "address1_composite desc";
        }
        if("address1_City".equals(orderName)) {
            orderBy = orderType == 0 ? "address1_City asc" : "address1_City desc";
        }
        if("ecolabcn_department".equals(orderName)) {
            orderBy = orderType == 0 ? "ecolabcn_department asc" : "ecolabcn_department desc";
        }
        if("thumbs_up_num".equals(orderName)) {
            orderBy = orderType == 0 ? "thumbs_up_num asc" : "thumbs_up_num desc";
        }
        if("comprehensiveEvaluation".equals(orderName)) {
            orderBy = orderType == 0 ? "case when thumbs_up_num = 0 then 0\n" +
                    "        else Round(convert(float,thumbs_up_points)/convert(float,thumbs_up_num),2)\n" +
                    "        end asc" : "case when thumbs_up_num = 0 then 0\n" +
                    "        else Round(convert(float,thumbs_up_points)/convert(float,thumbs_up_num),2)\n" +
                    "        end desc";
        }

        keyWord = StringUtils.isEmpty(keyWord) ? null : "%" + keyWord + "%";
        PageHelper.startPage(page, size, orderBy);
        List<BStoreListVo> vos = storesMapper.selectList(keyWord);
        PageInfo<BStoreListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    public List<Stores> list(String keyWord) {
        keyWord = StringUtils.isEmpty(keyWord) ? null : "%" + keyWord + "%";
        List<Stores> vos = storesMapper.selectListAll(keyWord);
        return vos;
    }

    public List<BStoresThumbsUpVo> thumbsUpStores(Float longitude, Float latitude) {
        List<BStoresThumbsUpVo> list = new ArrayList<>();
        list = storesMapper.getThumbsUpStores(latitude, longitude);
        return list;
    }

    public void thumbsUp(Integer id, Integer points) {
        Stores stores = sure(id);
        stores.setThumbs_up_num(stores.getThumbs_up_num() + 1);
        stores.setThumbs_up_points(stores.getThumbs_up_points() + points);
        stores.setUpdate_time(new Date());
        storesMapper.updateByPrimaryKey(stores);

        //添加点赞流水
        ThumbsUpHistory thumbsUpHistory = new ThumbsUpHistory();
        thumbsUpHistory.setStoreId(id);
        thumbsUpHistory.setThumbsUpPoint(points);
        thumbsUpHistory.setCreateTime(new Date());
        thumbsUpHistoryMapper.insert(thumbsUpHistory);
    }

    public Stores sure(Integer id) {
        Stores stores = storesMapper.selectByPrimaryKey(id);
        if(stores == null) {
            throw new ApiException(MessageCode.CODE_NOT_EXIST, "门店");
        }
        return stores;
    }

    public PageBean<BStoreListVo> searchByRegionList(Integer provinceId, Integer cityId, Integer districtId,
                                                     String keyWord, Integer page, Integer size) {
        // 校验省市区联动
        if(provinceId != null && cityId != null && districtId != null) {
            regionService.sureRegionIdMatch(provinceId, cityId, districtId);
        }
        PageHelper.startPage(page, size);
        List<BStoreListVo> vos = storesMapper.selectByRegionId(provinceId, cityId, districtId, keyWord);
        PageInfo<BStoreListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }
}
