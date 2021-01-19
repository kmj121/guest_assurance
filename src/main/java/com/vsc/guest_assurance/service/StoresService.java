package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.microsoft.graph.models.extensions.DirectoryObject;
import com.vsc.guest_assurance.common.*;
import com.vsc.guest_assurance.dao.StoresMapper;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.entity.UserAdmins;
import com.vsc.guest_assurance.entity.Users;
import com.vsc.guest_assurance.vo.BackendContactInformationListVo;
import com.vsc.guest_assurance.vo.BackendStoreListVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
            List<Stores> result = storesMapper.selectByAccoutNumber(item.getAccountnumber());
            if(result == null || result.size() == 0) {
                item.setCreate_time(new Date());
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

    public PageBean<BackendStoreListVo> list(String keyWord, Integer page, Integer size) {
        keyWord = StringUtils.isEmpty(keyWord) ? "" : "%" + keyWord + "%";
        PageHelper.startPage(page, size);
        List<BackendStoreListVo> vos = storesMapper.selectList(keyWord);
        PageInfo<BackendStoreListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    public List<Stores> list(String keyWord) {
        keyWord = StringUtils.isEmpty(keyWord) ? "" : "%" + keyWord + "%";
        List<Stores> vos = storesMapper.selectListAll(keyWord);
        return vos;
    }
}
