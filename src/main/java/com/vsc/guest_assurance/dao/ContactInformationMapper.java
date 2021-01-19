package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.ContactInformation;
import com.vsc.guest_assurance.vo.BackendContactInformationListVo;

import java.util.List;

public interface ContactInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContactInformation record);

    int insertSelective(ContactInformation record);

    ContactInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContactInformation record);

    int updateByPrimaryKey(ContactInformation record);

    List<BackendContactInformationListVo> selectList(String keyWord);
}