package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.*;
import com.vsc.guest_assurance.dao.StoresMapper;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.vo.backend.BStoreListVo;
import com.vsc.guest_assurance.vo.backend.BStoresThumbsUpVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void updateStores() throws Exception {
        List<Stores> storesList = ParseStores.storeParser();
        for (Stores item : storesList) {
            if(StringUtils.isBlank(item.getAccountid())) {
                continue;
            }
            List<Stores> result = storesMapper.selectByAccoutId(item.getAccountid());
            if(result == null || result.size() == 0) {
                item.setCreate_time(new Date());
                item.setThumbs_up_num(Constant.FALSE);
                item.setThumbs_up_points(Constant.FALSE);
                storesMapper.insert(item);
            } else if (result != null && result.size() == 1){
                PropertyUtils.copyProperties(result.get(0), item);
                item.setUpdate_time(new Date());
                storesMapper.updateByPrimaryKey(result.get(0));
            } else if(result != null && result.size() > 1) {
                throw new ApiException(MessageCode.CODE_ONE_MORE_DATA, "门店");
            }
        }
    }

    public PageBean<BStoreListVo> list(String keyWord, Integer page, Integer size) {
        keyWord = StringUtils.isEmpty(keyWord) ? null : "%" + keyWord + "%";
        PageHelper.startPage(page, size);
        List<BStoreListVo> vos = storesMapper.selectList(keyWord);
        for (BStoreListVo item : vos) {
            if(item.getThumbs_up_num() == 0) {
                item.setComprehensiveEvaluation("0");
            } else {
                item.setComprehensiveEvaluation(new DecimalFormat("0.00").format((float)item.getThumbs_up_points()/item.getThumbs_up_num()));
            }
        }
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
    }

    public Stores sure(Integer id) {
        Stores stores = storesMapper.selectByPrimaryKey(id);
        if(stores == null) {
            throw new ApiException(MessageCode.CODE_NOT_EXIST, "门店");
        }
        return stores;
    }
}
