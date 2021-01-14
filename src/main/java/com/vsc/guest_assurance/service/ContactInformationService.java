package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.dao.ContactInformationMapper;
import com.vsc.guest_assurance.entity.ContactInformation;
import com.vsc.guest_assurance.qo.BackendContactInformationAddQo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

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
}
