package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.dao.ContactInformationMapper;
import com.vsc.guest_assurance.entity.ContactInformation;
import com.vsc.guest_assurance.qo.backend.BContactInformationAddQo;
import com.vsc.guest_assurance.vo.backend.BContactInformationDetailVo;
import com.vsc.guest_assurance.vo.backend.BContactInformationListVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ContactInformationService {

    @Autowired
    private ContactInformationMapper contactInformationMapper;

    public Integer add(BContactInformationAddQo qo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ContactInformation contactInformation = new ContactInformation();
        PropertyUtils.copyProperties(contactInformation, qo);
        contactInformation.setCreateTime(new Date());
        contactInformationMapper.insert(contactInformation);
        return contactInformation.getId();
    }

    public PageBean<BContactInformationListVo> list(String keyWord, Integer page, Integer size) {
        keyWord = StringUtils.isEmpty(keyWord) ? null : "%" + keyWord + "%";
        PageHelper.startPage(page, size);
        List<BContactInformationListVo> vos = contactInformationMapper.selectList(keyWord);
        PageInfo<BContactInformationListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    public BContactInformationDetailVo detail(Integer id) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ContactInformation contactInformation = sureById(id);
        BContactInformationDetailVo bContactInformationDetailVo = new BContactInformationDetailVo();
        PropertyUtils.copyProperties(bContactInformationDetailVo, contactInformation);
        return bContactInformationDetailVo;
    }

    public ContactInformation sureById(Integer id) {
        ContactInformation contactInformation = contactInformationMapper.selectByPrimaryKey(id);
        if(contactInformation == null) {
            throw new ApiException(MessageCode.CODE_NOT_EXIST, "联系方式");
        }
        return contactInformation;
    }
}
