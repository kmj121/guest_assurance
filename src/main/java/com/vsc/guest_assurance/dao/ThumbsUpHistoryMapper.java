package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.ThumbsUpHistory;

public interface ThumbsUpHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThumbsUpHistory record);

    int insertSelective(ThumbsUpHistory record);

    ThumbsUpHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThumbsUpHistory record);

    int updateByPrimaryKey(ThumbsUpHistory record);
}