package com.vsc.guest_assurance.entity;

import java.util.Date;

public class ThumbsUpHistory {
    private Integer id;

    private Integer storeId;

    private Integer thumbsUpPoint;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getThumbsUpPoint() {
        return thumbsUpPoint;
    }

    public void setThumbsUpPoint(Integer thumbsUpPoint) {
        this.thumbsUpPoint = thumbsUpPoint;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}