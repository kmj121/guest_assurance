package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
public class BackendStoresThumbsUpVo {
    private Integer id;
    private String name;
    private String address;
    @ApiModelProperty("距离(单位：千米)")
    private Float distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
