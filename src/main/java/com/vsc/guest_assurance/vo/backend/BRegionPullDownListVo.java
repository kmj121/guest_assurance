package com.vsc.guest_assurance.vo.backend;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
public class BRegionPullDownListVo {
    private Integer regionId;
    private String regionName;
    private Integer pid;
    private Integer level;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
