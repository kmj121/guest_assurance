package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.*;
import com.vsc.guest_assurance.dao.StoresMapper;
import com.vsc.guest_assurance.dao.ThumbsUpHistoryMapper;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.entity.ThumbsUpHistory;
import com.vsc.guest_assurance.vo.backend.BStoreListVo;
import com.vsc.guest_assurance.vo.backend.BStoresThumbsUpVo;
import com.vsc.guest_assurance.vo.common.ParseStoresVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ThumbsUpHistoryMapper thumbsUpHistoryMapper;

    public void updateStores() throws Exception {
        List<ParseStoresVo> parseList = ParseStores.storeParser();
        for (ParseStoresVo item : parseList) {
            if(StringUtils.isBlank(item.getAccountid())) {
                continue;
            }
            List<Stores> storesList = storesMapper.selectByAccoutId(item.getAccountid());
            if(storesList == null || storesList.size() == 0) {
                Stores stores = new Stores();
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

    public PageBean<BStoreListVo> list(String keyWord, Integer page, Integer size, Integer accountnumbersort
        , Integer nameSort, Integer _ownerid_value_sort, Integer address1_composite_sort, Integer address1_City_sort
        , Integer ecolabcn_department_sort, Integer thumbs_up_num_sort, Integer comprehensiveEvaluationSort) {
        String orderBy = "id asc";
        if(accountnumbersort != null) {
            orderBy = accountnumbersort == 1 ? "accountnumber asc" : "accountnumber desc";
        }
        if(nameSort != null) {
            orderBy = nameSort == 1 ? "name asc" : "name desc";
        }
        if(_ownerid_value_sort != null) {
            orderBy = _ownerid_value_sort == 1 ? "_ownerid_value asc" : "_ownerid_value desc";
        }
        if(address1_composite_sort != null) {
            orderBy = address1_composite_sort == 1 ? "address1_composite asc" : "address1_composite desc";
        }
        if(address1_City_sort != null) {
            orderBy = address1_City_sort == 1 ? "address1_City asc" : "address1_City desc";
        }
        if(ecolabcn_department_sort != null) {
            orderBy = ecolabcn_department_sort == 1 ? "ecolabcn_department asc" : "ecolabcn_department desc";
        }
        if(thumbs_up_num_sort != null) {
            orderBy = thumbs_up_num_sort == 1 ? "thumbs_up_num asc" : "thumbs_up_num desc";
        }
        if(comprehensiveEvaluationSort != null) {
            orderBy = comprehensiveEvaluationSort == 1 ? "comprehensiveEvaluation asc" : "comprehensiveEvaluation desc";
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
}
