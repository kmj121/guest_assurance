package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.dao.ContactInformationMapper;
import com.vsc.guest_assurance.entity.ContactInformation;
import com.vsc.guest_assurance.qo.BackendContactInformationAddQo;
import com.vsc.guest_assurance.qo.BackendContactInformationDetailQo;
import com.vsc.guest_assurance.vo.BackendContactInformationListVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
public class ContactInformationService {

    @Autowired
    private ContactInformationMapper contactInformationMapper;

    public Integer add(BackendContactInformationAddQo qo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ContactInformation contactInformation = new ContactInformation();
        PropertyUtils.copyProperties(contactInformation, qo);
        contactInformation.setCreateTime(new Date());
        contactInformationMapper.insert(contactInformation);
        return contactInformation.getId();
    }

    public PageBean<BackendContactInformationListVo> list(String keyWord, Integer page, Integer size) {
        keyWord = StringUtils.isEmpty(keyWord) ? "" : "%" + keyWord + "%";
        PageHelper.startPage(page, size);
        List<BackendContactInformationListVo> vos = contactInformationMapper.selectList(keyWord);
        PageInfo<BackendContactInformationListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    public BackendContactInformationDetailQo detail(Integer id) {
        return new BackendContactInformationDetailQo();
    }
}
